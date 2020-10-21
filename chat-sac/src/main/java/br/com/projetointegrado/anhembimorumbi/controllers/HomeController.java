package br.com.projetointegrado.anhembimorumbi.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.projetointegrado.anhembimorumbi.models.Organization;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.service.DepartamentService;
import br.com.projetointegrado.anhembimorumbi.service.OrganizationService;
import br.com.projetointegrado.anhembimorumbi.service.UserService;
import br.com.projetointegrado.anhembimorumbi.utils.Json;
import br.com.projetointegrado.anhembimorumbi.utils.Path;
import br.com.projetointegrado.anhembimorumbi.utils.Status;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import lombok.Data;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.servlet.SparkApplication;

public class HomeController {

	public static class RegisterPayload {
		public String name;
		public String cnpj;
		public String phone;
		public String email;
		public String password;

		public boolean isValid() {
			return !((name == null) || (cnpj == null) || (email == null) || (password == null));
		}
	}

	public static Route Index = (Request request, Response response) -> {
		User current = UserService.getCurrentUser(request);
		if (current == null) {
			response.redirect(Path.Web.LOGIN);
		}
		response.redirect("index.html");
		return "Done";
	};

	public static Route Register = (Request request, Response response) -> {
		try {
			ObjectMapper mapper = new ObjectMapper();
			RegisterPayload payload = mapper.readValue(request.body(), RegisterPayload.class);

			if (!payload.isValid()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verificar se os campos estão preenchidos corretamente.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			User exist = UserService.getUserByUsername(payload.email);
			if (exist != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Usuário com este email já existe, por favor realizar login na plataforma.");
				return Json.Encode(data);
			}

			Organization org = new Organization();
			org.setName(payload.name);
			org.setCNPJ(payload.cnpj);
			org.setPhone(payload.phone);
			org.setEmail(payload.email);
			OrganizationService.Save(org);

			if (org.getId() > 0) {
				long departament_id = DepartamentService.Save("GERAL", org.getId());

				User user = new User();
				user.setStatus(Status.ONLINE.getValue());
				user.setUsername(payload.email);
				user.setPassword(payload.password);
				user.setOrgID(org.getId());
				user.setDepartamentID(departament_id);
				UserService.Save(user);

				if (user.getId() != null && user.getId() > 0) {
					response.status(200);
					response.type("application/json");
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("result", true);
					data.put("message", "Cadastro realizado com sucesso");
					return Json.Encode(data);
				} else {
					DepartamentService.Delete(departament_id);
					OrganizationService.Delete(org.getId());
				}

				response.status(500);
				response.type("application/json");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Error inesperado, por favor tente mais tarde.");
				return Json.Encode(data);
			}

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
		return request;
	};

	public static Route Login = (Request request, Response response) -> {
		User user = UserService.getCurrentUser(request);
		if(user != null) {
			response.redirect(Path.Web.DASHBOARD);
		}
		response.redirect("authentication.html");
		return "Done";
	};

	public static Route Authenticate = (Request request, Response response) -> {
		String username = request.queryParams("email");
		String password = request.queryParams("password");

		try {
			User user = UserService.Authenticate(username, password);
			if (user == null) {
				response.redirect("authentication.html?login=false");
				return false;
			}

			user.setStatus(Status.ONLINE.getValue());
			UserService.Save(user);

			UserService.setCurrentUser(request, user);

			UserService.LoginRedirect(request, response);
			return true;
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Logout = (Request request, Response response) -> {
		try{
		UserService.Logout(request);
		response.redirect(Path.Web.LOGIN);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
		return null;
	};

	public static Route Error404 = (Request request, Response response) -> {
		response.redirect(Path.Web.ERROR404);
		return null;
	};
}
