package com.nathanielramirez.averagejoeauto.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanielramirez.averagejoeauto.repositories.PostRepository;
import com.nathanielramirez.averagejoeauto.repositories.ThreadRepository;
import com.nathanielramirez.averagejoeauto.models.Thread;


@Service
public class ThreadService {

	@Autowired
	private ThreadRepository threadRepository;
	
	public List<Thread> allThreads() {
		return threadRepository.findAll();
	}
	
	public Thread createThread(Thread t) {
		return threadRepository.save(t);
	}
	
	public void deleteById(Long threadId, HttpSession session) {
		if(((Long) session.getAttribute("user")).equals(this.findThreadById(threadId).getUser().getId())) {
		    threadRepository.deleteById(threadId);
		}
	}
	
	public Thread findThreadById(Long id) {
		Optional<Thread> thread = threadRepository.findById(id);
		if(thread.isPresent()) {
			return thread.get();
		} else {
			return null;
		}
	}
	
	public Thread findThreadsByUserId(Long id) {
		Optional<Thread> threads = threadRepository.findByUserId(id);
		if(threads.isPresent()) {
			return threads.get();
		} else {
			return null;
		}
	}

}
