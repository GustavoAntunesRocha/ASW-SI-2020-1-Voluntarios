package br.com.asw.si.voluntarios.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.asw.si.voluntarios.enums.Perfil;
import br.com.asw.si.voluntarios.models.User;
import br.com.asw.si.voluntarios.repositories.UserRepository;
import br.com.asw.si.voluntarios.security.UserSS;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public User create(User user) {
		try {
			User obj = new User(user.getName(), user.getEmail(), user.getUsername(), pe.encode(user.getPassword()));
			obj.setCreationDate(new Date());
			obj.addAuthoritie(Perfil.USER);
			repository.save(obj);
			return obj;
		}
		catch(DataIntegrityViolationException e) {
			return null;
		}
	}
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
