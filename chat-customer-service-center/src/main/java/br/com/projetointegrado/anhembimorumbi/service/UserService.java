package br.com.projetointegrado.anhembimorumbi.service;

import java.util.List;

import spark.Request;
import spark.Response;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.utils.DBTransactionUtil;
import br.com.projetointegrado.anhembimorumbi.utils.Error;
import br.com.projetointegrado.anhembimorumbi.utils.Path;
import br.com.projetointegrado.anhembimorumbi.utils.Status;

public class UserService {

	public static void LoginRedirect(Request request, Response response) {
		String pathRedirect = PageService.getBackPage(request);
		if (pathRedirect != null) {
			PageService.clearBackPage(request);
			response.redirect(pathRedirect);
			return;
		}
		response.redirect(Path.Web.DASHBOARD);
	}

	public static User getCurrentUser(Request request) {
		User currentUser = request.session().attribute("currentUser");
		return currentUser;
	}
	
	public static void setCurrentUser(Request request, User user) {
		request.session().attribute("currentUser", user);
	}
	
	public static void Logout(Request request) throws Exception {
		request.session().removeAttribute("loggedOut");
		User user = UserService.getCurrentUser(request);
		if(user != null) {
			user.setStatus(Status.OFFLINE.getValue());
			Save(user);
			PageService.clearBackPage(request);
		}
	}

	public static User Authenticate(String username, String password) throws Exception {
		if (username == null || password == null) {
			throw new Exception("Username and password is required");
		} else if (username.isEmpty() || password.isEmpty()) {
			return null;
		}
		User user = getUserByUsername(username);
		if (user == null) {
			return null;
		}

		String hashedPassword = BCrypt.hashpw(password, user.getSalt());
		if (!hashedPassword.equals(user.getPassword())) {
			return null;
		}
		return user;
	}

	// This method doesn't do anything, it's just included as an example
	public static void setPassword(String username, String newPassword) throws Exception {
		User user = getUserByUsername(username);
		if (user == null) {
			throw new Exception(String.format("User not Found %s", username));
		}

		String newSalt = BCrypt.gensalt();
		String newHashedPassword = BCrypt.hashpw( newPassword, newSalt);
		user.setPassword(newHashedPassword);

		Long id = Update(user);
		if(id != user.getId()) {
			throw new Exception(String.format("ID Inesperado %d, esperado %d", id, user.getId()));
		}
	}

	public static Long Save(User user) throws Exception {
		if (user.getId() == null || user.getId() == 0) {
			return Create(user);
		}
		return Update(user);
	}

	public static Long Create(User user) throws Exception {
		if (!isValid(user) && (user.getId() != null && user.getId() != 0)) {
			throw new Exception("Não é possível criar um User, com os mesmos dados de um usuário já existente.");
		}
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User exist = getUserByUsername(user.getUsername());
		if (exist != null) {
			throw new Exception("Usuário já existe com esse username");
		}

		String newSalt = BCrypt.gensalt();
		String newHashedPassword = BCrypt.hashpw(user.getPassword(), newSalt);
		user.setSalt(newSalt);
		user.setPassword(newHashedPassword);

		session.save(user);

		session.getTransaction().commit();
		session.close();
		return user.getId();
	}

	public static Long Update(User user) {
		if (!isValid(user) && (user.getId() == null || user.getId() == 0)) {
			return null;
		}
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.update(user);

		session.getTransaction().commit();
		session.close();

		return user.getId();
	}

	public static User getById(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User result = null;
		result = (User) session.createQuery("FROM User WHERE ID = :user_id ").setParameter("user_id", id)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public static User getUserByUsername(String username) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User result = null;
		result = (User) session.createQuery("FROM User u WHERE u.Username = :user_name")
				.setMaxResults(1)
				.setParameter("user_name", username)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public static List<User> getAll() {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result = session.createQuery("from User").list();

		session.getTransaction().commit();
		session.close();
		return result;
	}

	public static boolean isValid(User user) {
		if (user.getUsername().isEmpty()) {
			return false;
		}

		if (user.getPassword().isEmpty()) {
			return false;
		}

		if (user.getOrgID() == 0) {
			return false;
		}

		return true;
	}

	public static List<User> GetByOrgID(Long orgID) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result =  session.createQuery( "FROM User WHERE ORG_ID = :org_id" ).setParameter("org_id", orgID).list();

		session.getTransaction().commit();
		session.close();	
		return result;
	}

	public static void Delete(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User user= (User)session.load(User.class,id);		
		session.delete(user);

		session.getTransaction().commit();
		session.close();
	}
}
