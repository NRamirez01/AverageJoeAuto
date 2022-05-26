package com.nathanielramirez.averagejoeauto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.nathanielramirez.averagejoeauto.models.Thread;

public interface ThreadRepository extends CrudRepository<Thread, Long> {
    List<Thread> findAll();
    Optional<Thread> findById(Long search);
    Optional<Thread> findByUserId(Long search);
    String deleteById(String search);
    String deleteByTitle(String search);
}

