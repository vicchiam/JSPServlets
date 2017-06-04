package org.example.model.beans;

public class Administrator {
	
	private int id;
	private String mail;
	private String password;
	private String name;
	private String state;
	private int id_question;
	
	public Administrator(String mail, String password, String name, String state, int id_question) {
		this.mail = mail;
		this.password = password;
		this.name = name;
		this.state = state;
		this.id_question = id_question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}	

}
