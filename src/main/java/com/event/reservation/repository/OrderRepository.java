package com.event.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.reservation.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
  
}
