package br.com.asw.si.voluntarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.asw.si.voluntarios.models.User;
import br.com.asw.si.voluntarios.repositories.UserRepository;
import br.com.asw.si.voluntarios.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSS(user.getId(), user.getUsername(), user.getPassword(), user.getAuthorities());
	}

}
