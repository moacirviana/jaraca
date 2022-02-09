package br.jus.tream.jaraca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.tream.jaraca.model.User;

public interface UserRepository extends JpaRepository<User, String>{
			
	User findByUsername(String username);
	
}
