package com.vences.rest.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vences.rest.dtos.UserDtoV1;
import com.vences.rest.dtos.UserDtoV2;
import com.vences.rest.entites.User;
import com.vences.rest.exceptions.UserNotFoundException;
import com.vences.rest.services.UserService;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Usuario no Encontrado");
		}
		User user = userOptional.get();
		
		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;
	}
	
	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> userOptional = userService.getUserById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Usuario no Encontrado");
		}
		User user = userOptional.get();
		
		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;
	}

}
