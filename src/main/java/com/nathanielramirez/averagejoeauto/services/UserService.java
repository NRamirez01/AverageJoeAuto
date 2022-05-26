package com.nathanielramirez.averagejoeauto.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanielramirez.averagejoeauto.models.User;
import com.nathanielramirez.averagejoeauto.repositories.UserRepository;
import com.nathanielramirez.averagejoeauto.models.LoginUser;

@Service
public class UserService {
    
	@Autowired
	private UserRepository userRepository;
	
	
//	Login
	public Optional<User> userByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User userByID(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User create(User user) {
		return this.userRepository.save(user);
	}
	
	
	
	public String validation(HttpSession session, 
			Model model, 
			RedirectAttributes redirectAttributes) {
		if(session.getAttribute("user") == null){
			model.addAttribute("user", new User());
			model.addAttribute("userLogin", new LoginUser());
			redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
			return "redirect:/";
		} else {
			return null;
		}
	}
	
}
