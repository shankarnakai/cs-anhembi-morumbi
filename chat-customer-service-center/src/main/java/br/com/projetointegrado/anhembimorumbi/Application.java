package br.com.projetointegrado.anhembimorumbi;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;
import com.restfb.types.Account;
import com.restfb.types.send.Message;
import com.restfb.types.send.PhoneMessageRecipient;
import com.restfb.types.send.SendResponse;

import spark.Request;
import spark.Spark;
import spark.servlet.SparkApplication;
import br.com.projetointegrado.anhembimorumbi.controllers.DepartamentController;
import br.com.projetointegrado.anhembimorumbi.controllers.HomeController;
import br.com.projetointegrado.anhembimorumbi.controllers.MessageController;
import br.com.projetointegrado.anhembimorumbi.controllers.OrganizationController;
import br.com.projetointegrado.anhembimorumbi.controllers.UserController;
import br.com.projetointegrado.anhembimorumbi.service.FacebookService;

public class Application implements SparkApplication {
	String appId = "762827643869560";
	String appkey = "2cf701c50ed9fd2252eed26408a5e17c";

	@Override
	public void init() {
		// Configure Spark
		// port(4567);
		staticFiles.location("/public");
		// staticFiles.expireTime(600L);

		// HOME
		Spark.get("/", HomeController.Index);

		Spark.get("/register", HomeController.Index);
		Spark.post("/register", HomeController.Register);

		Spark.get("/login", HomeController.Login);
		Spark.post("/login", HomeController.Authenticate);

		Spark.post("/logout", HomeController.Logout);
		Spark.get("/logout", HomeController.Logout);

		//MESSAGES
		Spark.get("/message/list", MessageController.Talks);
		Spark.get("/message/:id", MessageController.Messages);
		Spark.post("/message/send/", MessageController.Send);
		Spark.get("/message/receive", MessageController.Receive);

		//USER
		Spark.get("/user/current", UserController.CurrentUser);
		Spark.get("/user/list", UserController.List);
		Spark.get("/user/:id", UserController.ShowDetail);
		Spark.post("/user/new", UserController.Create);
		Spark.post("/user/password/:id", UserController.Password);
		Spark.post("/user/:id", UserController.Update);
		Spark.delete("/user/:id", UserController.Delete);

		/* ORGANIZATION */
		Spark.get("/receive", OrganizationController.Connect);
		Spark.get("/organization/list-pages", OrganizationController.ListPages);
		Spark.get("/organization/connect", OrganizationController.Connect);
		Spark.get("/organization/:id", OrganizationController.Info);
		Spark.post("/organization/:id", OrganizationController.Update);
		
		//DEPARTAMENTS
		Spark.get("/departament/list", DepartamentController.List);
		Spark.get("/departament/:id", DepartamentController.ShowDetail);
		Spark.post("/departament/new", DepartamentController.Create);
		Spark.post("/departament/:id", DepartamentController.Update);
		Spark.delete("/departament/:id", DepartamentController.Delete);
		
		// Spark.get("*", HomeController.Error404);
	}
}
