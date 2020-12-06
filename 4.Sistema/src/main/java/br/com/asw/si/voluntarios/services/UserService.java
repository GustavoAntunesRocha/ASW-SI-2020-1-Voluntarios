package br.com.asw.si.voluntarios.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asw.si.voluntarios.models.User;
import br.com.asw.si.voluntarios.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void create(User user) {
		User obj = new User(user.getName(), user.getEmail(), user.getUsername(), user.getPassword());
		obj.setCreationDate(new Date());
		repository.save(obj);
	}
}
