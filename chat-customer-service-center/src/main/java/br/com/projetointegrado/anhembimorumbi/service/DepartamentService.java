package br.com.projetointegrado.anhembimorumbi.service;
import java.util.List;

import org.hibernate.Session;

import br.com.projetointegrado.anhembimorumbi.models.Departament;
import br.com.projetointegrado.anhembimorumbi.utils.DBTransactionUtil;
import br.com.projetointegrado.anhembimorumbi.utils.Error;

public class DepartamentService {
	
	public static Long Save(Departament departament) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(departament);

		session.getTransaction().commit();
		session.close();
		return departament.getId();
	}
	
	public static void Delete(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Departament departament= (Departament)session.load(Departament.class,id);		
		session.delete(departament);

		session.getTransaction().commit();
		session.close();
	}

	public static Long Save(String name, Long orgID) {
		Departament departament = new Departament();
		departament.setName(name); 
		departament.setORG_ID(orgID); 
		return DepartamentService.Save(departament);
	}
	
	public static Departament GetById(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Departament result = null;
		result =  (Departament)session.createQuery( "FROM Departament WHERE ID == :depart_id " ).setParameter("depart_id",id).uniqueResult();

		session.getTransaction().commit();
		session.close();	
		return result; 
	}
	
	public static List<Departament> GetByOrgID(Long orgID) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result =  session.createQuery( "FROM Departament WHERE ORG_ID = :org_id" ).setParameter("org_id", orgID).list();

		session.getTransaction().commit();
		session.close();	
		return result;
	}

	public static Departament getByName(String name, Long org_ID) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Departament result = null;
		result =  (Departament) session.createQuery( "FROM Departament WHERE ORG_ID = :org_id AND Name = :name" )
				.setMaxResults(1)
				.setParameter("org_id", org_ID)
				.setParameter("name", name)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();	
		return result;
	}

	public static boolean IsValid() {
		return true;	
	}

}
