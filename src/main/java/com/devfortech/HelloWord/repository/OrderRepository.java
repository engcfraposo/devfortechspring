package com.devfortech.HelloWord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
