package br.com.projetointegrado.anhembimorumbi.models;

import java.util.Date;
import br.com.projetointegrado.anhembimorumbi.models.Organization;

public class Departament {
	private Long id;

	private String Name;
	private Long ORG_ID;
	private Date DateCreated;
	private Date DateUpdated;

	public Departament() {
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

	public Long getORG_ID() {
		return ORG_ID;
	}

	public void setORG_ID(Long oRG_ID) {
		ORG_ID = oRG_ID;
	}
}