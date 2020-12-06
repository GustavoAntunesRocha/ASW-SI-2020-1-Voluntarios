package br.com.asw.si.voluntarios.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CodeGenerator")
	@TableGenerator(table = "SEQUENCES_USERS", name = "CodeGenerator")
	private int id;

	@NotNull(message = "Name is mandatory")
	private String name;

	@NotNull(message = "E-mail is mandatory")
	@Column(unique = true)
	private String email;

	private Date creationDate;

	@NotNull(message = "User name is mandatory")
	@Column(unique = true)
	private String username;

	@NotNull(message = "Password is mandatory")
	@Size(min=6, max=30, message = "Password lenght must be between 6 and 30 characters ")
	private String password;

	
	private String authorities;
	
	public User() {}

	public User(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.creationDate = new Date();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
