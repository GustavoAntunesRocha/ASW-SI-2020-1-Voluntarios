package br.com.asw.si.voluntarios.services;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.asw.si.voluntarios.models.User;
import br.com.asw.si.voluntarios.repositories.UserRepository;


@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository userRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		User user = new User("Gustavo", "gustavo.antunes@grupoorla.com.br", "gustavo", "123", "ADMIN");
		user.setPassword(pe.encode(user.getPassword()));
		userRepository.save(user);
	}
}
