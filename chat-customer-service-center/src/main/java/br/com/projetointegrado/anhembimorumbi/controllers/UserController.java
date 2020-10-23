package br.com.projetointegrado.anhembimorumbi.controllers;

import spark.Route;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.service.UserService;
import br.com.projetointegrado.anhembimorumbi.utils.Json;
import br.com.projetointegrado.anhembimorumbi.utils.Path;
import spark.Request;
import spark.Response;

public class UserController {

	public static Route CurrentUser = (Request request, Response response) -> {
		User current = UserService.getCurrentUser(request);
		if (current == null) {
			return "null";
		}

		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("user", current);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}

	};
	
	public static Route List = (Request request, Response response) -> {
		try {
			User user = UserService.getCurrentUser(request);
			if (user != null) {
				response.redirect(Path.Web.DASHBOARD);
			}

			Long orgID = user.getOrgID();

			List<User> list = UserService.GetByOrgID(orgID);

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("users", list);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route ShowDetail = (Request request, Response response) -> {
		try {
			String strid = request.params("id");

			Long id = Long.parseLong(strid);

			User departament = UserService.getById(id);

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("departament", departament);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Delete = (Request request, Response response) -> {
		try {
			String strid = request.params("id");

			Long id = Long.parseLong(strid);

			UserService.Delete(id);

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Create = (Request request, Response response) -> {

		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(request.body(), User.class);
			if ( UserService.isValid(user)) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verificar se os campos estão preenchidos corretamente.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			User current = UserService.getCurrentUser(request);
			if (current != null) {
				response.redirect(Path.Web.DASHBOARD);
			}

			User exist = UserService.getUserByUsername(user.getUsername());

			Long id = UserService.Save(user);

			if (id != null && id != 0) {
				response.status(200);
				response.type("application/json");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", true);
				return Json.Encode(data);
			}

			response.status(500);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("result", false);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Update = (Request request, Response response) -> {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User newUser = mapper.readValue(request.body(), User.class);
			if (newUser.getName() == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verifique se você está preenchendo o campo nome.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			User user = UserService.getCurrentUser(request);
			if (user == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Você precisa estar logado para conseguir alterar os dados");
				data.put("redirect", "");
				return Json.Encode(data);
			} 

			newUser.setOrgID(user.getOrgID());
			UserService.Save(newUser);
			if(newUser.getId() == user.getId()) {
				UserService.setCurrentUser(request, newUser);
			}

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("result", true);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};
	
	
	public static class PasswordPayload {
		public String pass;
		public boolean isValid() {
			return (pass != null);
		}
	}

	public static Route Password = (Request request, Response response) -> {
			try {
			ObjectMapper mapper = new ObjectMapper();

			PasswordPayload payload = mapper.readValue(request.body(), PasswordPayload.class);
			if (payload.pass == null) {
				return "";
			}

			String strid = request.params("id");
			Long id = Long.parseLong(strid);

			User user = UserService.getById(id);
			if (user == null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Usuário não encontrado");
				data.put("redirect", "");
				return Json.Encode(data);
			} 

			UserService.setPassword(user.getUsername(), payload.pass);
			User current = UserService.getCurrentUser(request);
			if(user.getId() == current.getId()) {
				UserService.setCurrentUser(request, user);
			}
			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("result", true);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}	
	};
}
