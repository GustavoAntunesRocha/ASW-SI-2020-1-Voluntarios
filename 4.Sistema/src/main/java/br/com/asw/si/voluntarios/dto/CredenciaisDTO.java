package br.com.asw.si.voluntarios.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private int id;
	
	public CredenciaisDTO() {}
	
	public CredenciaisDTO(String username, String password, int id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}