package com.nathanielramirez.averagejoeauto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.nathanielramirez.averagejoeauto.models.Post;



public interface PostRepository extends CrudRepository<Post, Long> {
	List<Post> findAll();
	Optional<Post> findById(Long search);
	Optional<Post> findOneByUserId(Long search);
	List<Post> findAllByUserId(Long search);
	List<Post> findAllByThreadId(Long search);
	Void deleteAllByThreadId(Long threadId);
}
