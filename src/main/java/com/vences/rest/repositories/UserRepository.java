package com.vences.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vences.rest.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
