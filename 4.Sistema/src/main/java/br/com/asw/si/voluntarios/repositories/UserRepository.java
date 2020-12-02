package br.com.asw.si.voluntarios.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.asw.si.voluntarios.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Transactional
	User findByUsername(String username);
}
