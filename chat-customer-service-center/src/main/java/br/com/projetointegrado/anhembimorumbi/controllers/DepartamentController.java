package br.com.projetointegrado.anhembimorumbi.controllers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetointegrado.anhembimorumbi.models.Departament;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.service.DepartamentService;
import br.com.projetointegrado.anhembimorumbi.service.UserService;
import br.com.projetointegrado.anhembimorumbi.utils.Json;
import br.com.projetointegrado.anhembimorumbi.utils.Path;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import spark.Request;
import spark.Response;
import spark.Route;

public class DepartamentController {

	public static Route List = (Request request, Response response) -> {
		try {
			User user = UserService.getCurrentUser(request);
			if (user == null) {
				return "";
			}

			Long orgID = user.getOrgID();

			java.util.List<Departament> list = DepartamentService.GetByOrgID(orgID);

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("departaments", list);
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

			Departament departament = DepartamentService.GetById(id);

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

			DepartamentService.Delete(id);

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
			Departament departament = mapper.readValue(request.body(), Departament.class);
			if (departament.getName() != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verificar se os campos estão preenchidos corretamente.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			User user = UserService.getCurrentUser(request);
			if (user != null) {
				response.redirect(Path.Web.DASHBOARD);
			}

			departament.setORG_ID(user.getOrgID());

			Departament exist = DepartamentService.getByName(departament.getName(), departament.getORG_ID());

			Long id = DepartamentService.Save(departament);

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
			Departament newDepartament = mapper.readValue(request.body(), Departament.class);
			if (newDepartament.getName() != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", false);
				data.put("message", "Por favor, verifique se você está preenchendo o campo nome.");
				data.put("redirect", "");
				return Json.Encode(data);
			}

			User user = UserService.getCurrentUser(request);
			if (user != null) {
				response.redirect(Path.Web.DASHBOARD);
			}

			newDepartament.setORG_ID(user.getOrgID());
			DepartamentService.Save(newDepartament);

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
