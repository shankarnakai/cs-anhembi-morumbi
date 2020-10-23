package br.com.projetointegrado.anhembimorumbi.models;

import java.util.Date;

public class Organization {
	private Long id;

	private String Name;
	private String CNPJ;
	private String Email;
	private String Phone;
	private String FACEBOOK_TOKEN;
	private String FACEBOOK_ACCESS;
	private String FACEBOOK_PAGE;

	private Date DateCreated;
	private Date DateUpdated;

	public Organization() {
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ.replaceAll("[^0-9]", "");
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone.replaceAll("[^0-9]", "");
	}

	public String getFACEBOOK_TOKEN() {
		return FACEBOOK_TOKEN;
	}

	public void setFACEBOOK_TOKEN(String fACEBOOK_TOKEN) {
		FACEBOOK_TOKEN = fACEBOOK_TOKEN;
	}

	public Date getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return DateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		DateUpdated = dateUpdated;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFACEBOOK_PAGE() {
		return FACEBOOK_PAGE;
	}

	public void setFACEBOOK_PAGE(String fACEBOOK_PAGE) {
		FACEBOOK_PAGE = fACEBOOK_PAGE;
	}

	public String getFACEBOOK_ACCESS() {
		return FACEBOOK_ACCESS;
	}

	public void setFACEBOOK_ACCESS(String fACEBOOK_ACCESS) {
		FACEBOOK_ACCESS = fACEBOOK_ACCESS;
	}
}
