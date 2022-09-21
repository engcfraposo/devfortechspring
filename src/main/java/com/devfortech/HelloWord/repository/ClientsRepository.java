package com.devfortech.HelloWord.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
	
	Optional<Clients> findByUsername(String username);
}
