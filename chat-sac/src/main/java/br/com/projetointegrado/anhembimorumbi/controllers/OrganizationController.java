package br.com.projetointegrado.anhembimorumbi.controllers;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Account;

import br.com.projetointegrado.anhembimorumbi.models.Organization;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.service.OrganizationService;
import br.com.projetointegrado.anhembimorumbi.service.UserService;
import br.com.projetointegrado.anhembimorumbi.utils.Json;
import br.com.projetointegrado.anhembimorumbi.utils.Path;
import br.com.projetointegrado.anhembimorumbi.service.FacebookService;
import spark.Request;
import spark.Response;
import spark.Route;

public class OrganizationController {
	public static class Page {
		public String id;
		public String name;

		public Page() {
		}
	}

	public static class UpdatePayload {
		public String name;
		public String cnpj;
		public String phone;
		public String email;
		public String password;
		public String facebookid;
		public String facebook_key;

		public boolean isValid() {
			return !((name == null) || (cnpj == null) || (email == null) || (password == null));
		}
	}

	public static Route ListPages = (Request request, Response response) -> {
		try {
			Organization org = OrganizationService.getCurrent(request);
			String token = org.getFACEBOOK_TOKEN();
			FacebookClient client = new DefaultFacebookClient(token, Version.VERSION_2_8);
			Connection<Account> accounts = client.fetchConnection("me/accounts", Account.class);
			Map<String, Object> data = new HashMap<String, Object>();
			List<Page> pages = new ArrayList<Page>();

			for (List<Account> account : accounts) {
				for (Account acc : account) {
					Page page = new Page();
					page.id = acc.getId();
					page.name = acc.getName();
					pages.add(page);
				}
			}
			data.put("pages", pages);

			response.status(200);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Connect = (Request request, Response response) -> {

		try {
			Organization org = OrganizationService.getCurrent(request);
			if (org.getFACEBOOK_TOKEN() == null) {
				String redirectUrl = Path.Web.RECEIVE_CONNECT;
				String code = request.queryParams("code");

				if (code == null) {
					String loginDialogUrlString = FacebookService.GenerateLoginUrl(redirectUrl);
					return loginDialogUrlString;
				} else {
					org.setFACEBOOK_ACCESS(code);
					String token = FacebookService.RequestTokenSession(request, code, redirectUrl);
					org.setFACEBOOK_TOKEN(token);
					OrganizationService.Update(org);

					return "";
				}
			}
			return "";

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Info = (Request request, Response response) -> {
		User current = UserService.getCurrentUser(request);
		if (current == null) {
			response.redirect(Path.Web.LOGIN);
			return false;
		}

		try {
			Organization org = OrganizationService.GetById(current.getOrgID());
			if (org != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("model", org);
				response.status(200);
				return Json.Encode(data);
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("message", "Não foi possível encontrar nenhuma empresa com esse ID");
				response.status(400);
				return Json.Encode(data);
			}

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}

	};

	public static Route Update = (Request request, Response response) -> {

		try {
			ObjectMapper mapper = new ObjectMapper();
			Organization payload = mapper.readValue(request.body(), Organization.class);

			if (payload.getId() == 0) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verificar se os campos estão preenchidos corretamente.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			Organization org = OrganizationService.GetById(payload.getId());
			if (org == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Organização inexistente, por favor verificar se a url está correta.");
				return Json.Encode(data);
			}

			org.setName(payload.getName());
			org.setCNPJ(payload.getCNPJ());
			org.setPhone(payload.getPhone());
			org.setEmail(payload.getEmail());
			org.setFACEBOOK_PAGE(payload.getFACEBOOK_PAGE());
			OrganizationService.Update(org);

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("result", true);
			data.put("message", "Cadastro realizado com sucesso");
			return Json.Encode(data);

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};
}
