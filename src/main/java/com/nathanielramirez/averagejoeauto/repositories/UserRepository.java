package com.nathanielramirez.averagejoeauto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.nathanielramirez.averagejoeauto.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();
	Optional<User> findByEmail(String search);
	Optional<User> findById(Long search);
}
