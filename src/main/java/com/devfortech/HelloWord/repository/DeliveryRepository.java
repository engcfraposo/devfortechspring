package com.devfortech.HelloWord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {}
