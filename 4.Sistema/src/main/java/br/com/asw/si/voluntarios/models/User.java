package br.com.asw.si.voluntarios.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.asw.si.voluntarios.enums.Perfil;



@Entity
@Table(name="USERS")
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
	@Size(min=6, message = "Min password lenght is 6 characters ")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "AUTHORITIES")
	private Set<Integer> authorities = new HashSet<>();
		
	private boolean enabled;
	
	public User() {
		addAuthoritie(Perfil.USER);
	}

	public User(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.creationDate = new Date();
		this.username = username;
		this.password = password;
		this.enabled = true;
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

	public Set<Perfil> getAuthorities() {
		return authorities.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addAuthoritie(Perfil perfil) {
		this.authorities.add(perfil.getCod());
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
