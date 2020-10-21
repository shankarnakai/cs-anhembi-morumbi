package br.com.projetointegrado.anhembimorumbi.service;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.projetointegrado.anhembimorumbi.models.Message;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.utils.DBTransactionUtil;

public class MessageService {
	
	public static Long Save(Message message) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(message);

		session.getTransaction().commit();
		session.close();
		return message.getId();
	}
	
	public static void Delete(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Message message= (Message)session.load(Message.class,id);		
		session.delete(message);

		session.getTransaction().commit();
		session.close();
	}

	public static Long SendFacebook(User user, String toUser, String name, String text) {
		Message message = new Message();

		message.setDate_send((new Date()));

		message.setName(user.getName());
		message.setUSER_ID(user.getId());
		message.setText(text);
		message.setFACEBOOK_USER_ID(toUser);
		return MessageService.Save(message);
	}
	
	public static Message GetById(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Message result = null;
		result =  (Message)session.createQuery( "FROM Message WHERE ID == :message_id " ).setParameter("message_id",id).uniqueResult();

		session.getTransaction().commit();
		session.close();	
		return result; 
	}
	
	public static List<Message> GetByFacebookUser(String ID) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result =  session.createQuery( "FROM Message WHERE FACEBOOK_USER_ID = :id" ).setParameter("id", ID).list();

		session.getTransaction().commit();
		session.close();	
		return result;
	}
	
	public static List<Message> GetTalks() {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result =  session.createQuery( "FROM Message message WHERE USER_ID IS NULL ORDER BY message.FACEBOOK_USER_ID" ).list();

		session.getTransaction().commit();
		session.close();	
		return result;
	}

	public static boolean IsValid() {
		return true;	
	}

}
