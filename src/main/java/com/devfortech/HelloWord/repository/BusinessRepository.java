package com.devfortech.HelloWord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {}
