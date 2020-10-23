package br.com.projetointegrado.anhembimorumbi.service;

import java.util.List;

import spark.Request;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.utils.DBTransactionUtil;
import br.com.projetointegrado.anhembimorumbi.utils.Error;

public class PageService {

	private static String backPage = "back_page";

	public static String getBackPage(Request request) {
		String path = request.session().attribute(backPage);
		return path;
	}
	
	public static void setBackPage(Request request, String path) {
		request.session().attribute(backPage, path);
	}
	
	public static void clearBackPage(Request request) {
		request.session().removeAttribute(backPage);
	}
}
