package br.com.projetointegrado.anhembimorumbi.models;

import java.util.Date;
import br.com.projetointegrado.anhembimorumbi.utils.Status;

public class User {
	private Long id;

	private String Name;
	private int Status;
	private String Username;
	private String Password;
	private Long orgID;
	private Long departamentID;
	private Date DateCreated;
	private Date DateUpdated;
	private String Salt;
	
	public Departament departament;

	public User() {
	}
	
	public Long getId(){
		return this.id; 
	}

	public void setId(Long id){
		this.id = id; 
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Long getOrgID() {
		return orgID;
	}

	public void setOrgID(Long orgID) {
		this.orgID = orgID;
	}

	public Long getDepartamentID() {
		return departamentID;
	}

	public void setDepartamentID(Long departamentID) {
		this.departamentID = departamentID;
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

	public String getSalt() {
		return Salt;
	}

	public void setSalt(String salt) {
		Salt = salt;
	}
}
