package com.vences.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vences.rest.entites.User;
import com.vences.rest.exceptions.UserExistsException;
import com.vences.rest.exceptions.UserNotFoundException;
import com.vences.rest.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser != null) {
			throw new UserExistsException("Usuario ya existe en el repositorio.");
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("Usuario no encontrado en el repositorio");
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("Usuario no encontrado en el repositorio, ingrese el id de usuario correcto.");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado en el repositorio, ingrese el id de usuario correcto.");
		}
		
		userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
