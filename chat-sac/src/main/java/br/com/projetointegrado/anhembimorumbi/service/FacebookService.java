package br.com.projetointegrado.anhembimorumbi.service;

import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ExtendedPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;

import br.com.projetointegrado.anhembimorumbi.utils.Config;
import spark.Request;

import com.restfb.DefaultFacebookClient;

public class FacebookService {
	public static String GenerateLoginUrl(String redirectUrl) {
		ScopeBuilder scopeBuilder = new ScopeBuilder();
		scopeBuilder.addPermission(UserDataPermissions.USER_HOMETOWN).addPermission(UserDataPermissions.USER_ABOUT_ME)
				.addPermission(UserDataPermissions.USER_BIRTHDAY).addPermission(ExtendedPermissions.PAGES_MESSAGING)
				.addPermission(ExtendedPermissions.PAGES_MESSAGING_PHONE_NUMBER)
				.addPermission(ExtendedPermissions.PAGES_SHOW_LIST).addPermission(ExtendedPermissions.MANAGE_PAGES);
		// .addPermission(ExtendedPermissions.XMPP_LOGIN);

		FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_8);
		String loginDialogUrlString = client.getLoginDialogUrl(Config.Facebook.APP_ID, redirectUrl, scopeBuilder,
				Parameter.with("response_type", "code"));
		return loginDialogUrlString;
	}

	public static String RequestTokenSession(Request request, String code, String redirectUrl) {
		if (code == null) {
			return "Erro encontrado";
		}

		AccessToken fb_token = GetToken(request);

		String token = "";
		if (fb_token == null) {
			FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_8);
			AccessToken acessToken = client.obtainUserAccessToken(Config.Facebook.APP_ID, Config.Facebook.APP_KEY, redirectUrl, code);
			setToken(request,acessToken);
			token = acessToken.getAccessToken();
		} else {
			token = fb_token.getAccessToken();
		}
		
		return token;
	}
	
	
	public static AccessToken GetToken(Request request) {
		return request.session().attribute("fb_token");
	}
	
	public static void setToken(Request request, AccessToken value) {
		request.session().attribute("fb_token", value);
	}
}
