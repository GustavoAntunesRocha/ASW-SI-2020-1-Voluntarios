package br.com.asw.si.voluntarios.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CodeGenerator")
	@TableGenerator(table = "SEQUENCES_PERSONS", name = "CodeGenerator")
	protected int id;
	
	protected String name;
	
	@OneToMany
	protected List<Address> addresses;
	
	@OneToMany
	protected List<Phone> phones;
	
	@Column(unique = true)
	protected String cpf;

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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
