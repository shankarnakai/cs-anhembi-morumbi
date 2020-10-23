package br.com.projetointegrado.anhembimorumbi.service;
import java.util.List;

import org.hibernate.Session;


import br.com.projetointegrado.anhembimorumbi.models.Organization;
import br.com.projetointegrado.anhembimorumbi.models.User;
import br.com.projetointegrado.anhembimorumbi.utils.DBTransactionUtil;
import br.com.projetointegrado.anhembimorumbi.utils.Error;
import spark.Request;

public class OrganizationService {
	
	public static Organization getCurrent(Request request) throws Exception {
		User user = UserService.getCurrentUser(request);
		if(user == null) {
			throw new Exception("É necessário estar logado para achar a organização atual");
		}
		
		Organization org = GetById(user.getOrgID());
		return org;
	}
	
	public static Long Save(Organization org) throws Exception {
		if (org.getId() == null || org.getId() == 0) {
			return Create(org);
		}
		return Update(org);
	}

	public static Long Create(Organization org) throws Exception {
		if (!isValid(org) && (org.getId() != null && org.getId() != 0)) {
			throw new Exception("Não é possível criar um Organization, com os mesmos dados de um usuário já existente.");
		}
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Organization exist = getByCNPJ(org.getCNPJ());
		if (exist != null) {
			throw new Exception("Organização já existe com esse orgname");
		}

		session.save(org);

		session.getTransaction().commit();
		session.close();
		return org.getId();
	}

	public static Long Update(Organization org) {
		if (!isValid(org) && (org.getId() == null || org.getId() == 0)) {
			return null;
		}
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.update(org);

		session.getTransaction().commit();
		session.close();
		return org.getId();
	}
	
	public static void Delete(Long id) throws Exception {
		if (id == null && id > 0) {
			throw new Exception("é obrigatorio o id da organização");
		}
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Organization org= (Organization)session.load(Organization.class,id);		
		session.delete(org);

		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("null")
	public static Organization getByCNPJ(String cnpj) throws Exception {
		if (cnpj == null && cnpj.length() > 0) {
			throw new Exception("é obrigatorio o cnpj da organização");
		}

		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Organization result = null;
		result = (Organization) session.createQuery("FROM Organization org WHERE org.CNPJ = :cnpj")
				.setMaxResults(1)
				.setParameter("cnpj", cnpj)
				.uniqueResult();

		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static List<Organization> GetAll() {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List result = null;
		result =  session.createQuery( "FROM Organization" ).list();

		session.getTransaction().commit();
		session.close();	
		return result;
	}

	public static Organization GetById(Long id) {
		Session session = DBTransactionUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Organization result = null;
		result =  (Organization)session.createQuery( "FROM Organization WHERE ID = :org_id " ).setParameter("org_id",id).uniqueResult();

		session.getTransaction().commit();
		session.close();	
		return result; 
	}

	public static boolean isValid(Organization org) {
		return (org.getCNPJ() != null && org.getEmail() != null && org.getName() != null);
	}

}
