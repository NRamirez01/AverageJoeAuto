package com.nathanielramirez.averagejoeauto.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanielramirez.averagejoeauto.models.Post;
import com.nathanielramirez.averagejoeauto.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> allPosts(){
		return postRepository.findAll();
	}
	
	public Post createPost(Post p) {
		return postRepository.save(p);
	}
	
	public void deleteById(Long postId) {
		postRepository.deleteById(postId);
	}
	
	public void deleteAllByThreadId(Long threadId, HttpSession session) {
		if(((Long) session.getAttribute("user")).equals(this.findPost(threadId).getUser().getId())) {
		    postRepository.deleteAllByThreadId(threadId);
		}
	}
		
	public Post findPost(Long id) {
		Optional<Post> post = postRepository.findOneByUserId(id);
		if(post.isPresent()) {
			return post.get();
		} else {
			return null;
		}
	}
		
	public List<Post> findPostsByUserId(Long id) {
		return postRepository.findAllByUserId(id);
	}
	

	
	public List<Post> findPostsByThread(Long id) {
		return postRepository.findAllByThreadId(id);
	}

}
