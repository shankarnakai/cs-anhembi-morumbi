package br.com.projetointegrado.anhembimorumbi.models;

import java.util.Date;
import br.com.projetointegrado.anhembimorumbi.models.Organization;

public class Message {
	private Long id;

	private String Name;
	private Long USER_ID;
	private String Text;
	private Date date_send;	
	private Date date_visualization;	 
	private Date date_answer;	 
	private String FACEBOOK_USER_ID;

	public Message() {
	}

	public Long getId() {
		return id;
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

	public Long getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(Long uSER_ID) {
		USER_ID = uSER_ID;
	}

	public Date getDate_send() {
		return date_send;
	}

	public void setDate_send(Date date_send) {
		this.date_send = date_send;
	}

	public Date getDate_visualization() {
		return date_visualization;
	}

	public void setDate_visualization(Date date_visualization) {
		this.date_visualization = date_visualization;
	}

	public Date getDate_answer() {
		return date_answer;
	}

	public void setDate_answer(Date date_answer) {
		this.date_answer = date_answer;
	}

	public String getFACEBOOK_USER_ID() {
		return FACEBOOK_USER_ID;
	}

	public void setFACEBOOK_USER_ID(String fACEBOOK_USER_ID) {
		FACEBOOK_USER_ID = fACEBOOK_USER_ID;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}



	
}