package com.vences.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vences.rest.entites.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
