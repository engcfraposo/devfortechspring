package com.devfortech.HelloWord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
