package br.com.projetointegrado.anhembimorumbi.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.projetointegrado.anhembimorumbi.models.Message;
import br.com.projetointegrado.anhembimorumbi.models.Organization;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.service.DepartamentService;
import br.com.projetointegrado.anhembimorumbi.service.MessageService;
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

public class MessageController {

	public static class MessagePayload {
		public Long id;
		public String name;
		public String message;
		public String date;
	}

	public static class TalksPayload {
		public String id;
		public String title;
	}

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

	public static Route Talks = (Request request, Response response) -> {
		try {
			User user = UserService.getCurrentUser(request);
			if (user == null) {
				return "";
			}

			List<Message> list = MessageService.GetTalks();
			List<TalksPayload> talks = new ArrayList<TalksPayload>();
			Map<String, String> IDS_listeds = new HashMap<String,String>();
			for (Message message : list) {
				if(IDS_listeds.get(message.getFACEBOOK_USER_ID()) != null) {
					continue;
				}
				TalksPayload talk = new TalksPayload();

				talk.id = message.getFACEBOOK_USER_ID();
				talk.title = String.format("%s", message.getName());
				talks.add(talk);
				IDS_listeds.put(message.getFACEBOOK_USER_ID(), "OK");
			}

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("talks", talks);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Messages = (Request request, Response response) -> {
		try {
			User user = UserService.getCurrentUser(request);
			if (user == null) {
				return "";
			}

			String idTalk = request.params("id");

			List<Message> list = MessageService.GetByFacebookUser(idTalk);
			List<MessagePayload> messages = new ArrayList<MessagePayload>();
			for (Message message : list) {
				MessagePayload messagePayload = new MessagePayload();

				messagePayload.id = message.getId();
				messagePayload.name = message.getName();

				String dateString = "";
				SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
				dateString = sdfr.format(message.getDate_send());
				messagePayload.date = dateString;
				messagePayload.message = message.getText();
				messages.add(messagePayload);
			}

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("messages", messages);
			return Json.Encode(data);
		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Send = (Request request, Response response) -> {
		try {
			ObjectMapper mapper = new ObjectMapper();
			MessagePayload payload = mapper.readValue(request.body(), MessagePayload.class);

			User user = UserService.getCurrentUser(request);
			if (user == null) {
				return "";
			}

			Long id = MessageService.SendFacebook(user, payload.id.toString(), user.getName(), payload.message);

			if (id > 0) {
				response.status(200);
				response.type("application/json");
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("result", true);
				return Json.Encode(data);
			}

			response.status(200);
			response.type("application/json");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("result", false);
			return Json.Encode(data);

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
	};

	public static Route Receive = (Request request, Response response) -> {
		try {
			ObjectMapper mapper = new ObjectMapper();
			RegisterPayload payload = mapper.readValue(request.body(), RegisterPayload.class);

		} catch (Exception e) {
			response.status(500);
			throw new ExceptionInInitializerError(e);
		}
		return request;
	};

}
