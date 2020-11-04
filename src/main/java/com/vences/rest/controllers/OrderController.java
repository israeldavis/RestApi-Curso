package com.vences.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vences.rest.entites.Order;
import com.vences.rest.entites.User;
import com.vences.rest.exceptions.UserNotFoundException;
import com.vences.rest.repositories.OrderRepository;
import com.vences.rest.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("Usuario no encontrado");
		
		return userOptional.get().getOrders();
	}
	
	// Crate Order
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Usuario no encontrado");
		}
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("Usuario no encontrado");
		}
		User user = userOptional.get();
		Order orden = new Order();
		List<Order> orders = user.getOrders();
		for (Order order : orders) {
			if(order.getOrderId().longValue() == orderid.longValue()) {
				orden = order;
			}
		}
		return orden;
		
	}

}
