package com.nathanielramirez.averagejoeauto.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanielramirez.averagejoeauto.models.*;
import com.nathanielramirez.averagejoeauto.models.Thread;
import com.nathanielramirez.averagejoeauto.services.*;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired PostService postService;
	
	@Autowired ThreadService threadService;
	
	static final String LOGINREG = "loginRegistration.jsp";
	static final String FORUM = "redirect:/averageJoeAuto/forum";
	static final String REDIRECT = "redirect:/";
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return REDIRECT;
	}
	
	@GetMapping("/")
	public String home(
			@ModelAttribute("user") User user,
			@ModelAttribute("userLogin") LoginUser userLogin
			) {
		return LOGINREG;
	}
	
	@PostMapping("/loginUser")
	public String login(@Valid @ModelAttribute("userLogin") LoginUser userLogin, 
			BindingResult result, 
			HttpSession session, 
			Model model) {
		
		Optional<User> validate = userService.userByEmail(userLogin.getEmail());
		if(validate.isPresent() && BCrypt.checkpw(userLogin.getPassword(), validate.get().getPassword())) {
			session.setAttribute("user", validate.get().getId());
			return FORUM;
		}
		result.rejectValue("email","Email", "Invalid email/password");
		model.addAttribute("user", new User());
		return LOGINREG;
	}
	
	@GetMapping("/addThread")
	public String threadAdd(@ModelAttribute("thread") Thread thread) {
		return "threadAdd.jsp";
	}
	
	@PostMapping("/createThread")
	public String threadCreate(@Valid @ModelAttribute("thread") Thread thread, 
			BindingResult result, 
			HttpSession session){
		if (result.hasErrors()) {
			return "threadAdd.jsp";
		} else {
			threadService.createThread(thread);
			return FORUM;
		}
	}
	
	@PostMapping("/averageJoeAuto/forum/newPost")
	public String threadCreate(@ModelAttribute("post") Post post,
			Model model,
			BindingResult result, 
			HttpSession session){
		if (result.hasErrors()) {
			return "threadviewer.jsp";
		} else {
			postService.createPost(post);

			return FORUM;
		}
	}
	
	@GetMapping("edit/{threadId}")
	public String editThread(@ModelAttribute("thread") Thread thread, 
			@PathVariable("threadId") Long id, 
			Model model, 
			RedirectAttributes redirectAttributes,
			HttpSession session
			){
		if(userService.validation(session, model, redirectAttributes) != null) {
			return REDIRECT;
		}	
		model.addAttribute("thread", threadService.findThreadById(id));
			return "editThread.jsp";
	}
	
	@GetMapping("edit/post/{postId}")
	public String editPost(@ModelAttribute("post") Post post,
			@PathVariable("postId") Long id,
			Model model,
			@ModelAttribute("thread") Thread thread,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		if(userService.validation(session, model, redirectAttributes) != null) {
			return REDIRECT;
		}
		model.addAttribute("thread", thread);
		model.addAttribute("post", postService.findPost(id));
		return "editPost.jsp";
	}
	
	@PutMapping(value="/edit/post/submit/{id}")
    public String update(@Valid @ModelAttribute("post") Post post, 
    		BindingResult result, 
    		Model model, 
    		RedirectAttributes redirectAttributes, 
    		HttpSession session) {
		if(userService.validation(session, model, redirectAttributes) != null) {
			return REDIRECT;
		}
		if (result.hasErrors()) {
    		model.addAttribute("post", post);
    		return "editThread.jsp";
    	} else {
    	     postService.createPost(post);
    	     return FORUM;
    	}
    }
	
	@PutMapping(value="/edit/submit/{id}")
    public String update(@Valid @ModelAttribute("thread") Thread thread, 
    		BindingResult result, 
    		Model model, 
    		RedirectAttributes redirectAttributes, 
    		HttpSession session) {
		if(userService.validation(session, model, redirectAttributes) != null) {
			return REDIRECT;
		}
		if (result.hasErrors()) {
    		model.addAttribute("thread", thread);
    		return "editThread.jsp";
    	} else {
    	     threadService.createThread(thread);
    	     return FORUM;
    	}
    }
	
	@Transactional
    @RequestMapping(value="/delete/thread/{threadId}", method= {RequestMethod.POST, RequestMethod.DELETE})
    public String deletePost(@PathVariable("threadId") Long threadId,
    		HttpSession session) {
		threadService.deleteById(threadId, session);
    	return FORUM;
	}
	
	@Transactional
    @RequestMapping(value="/delete/{threadId}/{postId}", method= {RequestMethod.POST, RequestMethod.DELETE})
    public String deletePost(@PathVariable("postId") Long postId,
    		@PathVariable("threadId") Long threadId,
    		HttpSession session) {
		postService.deleteById(postId);
    	return FORUM;
	}
	
	@PostMapping("/registerUser")
	public String register(@Valid @ModelAttribute("user") User user, 
			BindingResult result, 
			HttpSession session, 
			Model model) {
		Optional<User> validate = userService.userByEmail(user.getEmail());
		if(validate.isPresent()){
			result.rejectValue("email", "Email", "Please enter a valid email");
		}
		if(!user.getPassword().equals(user.getConfirm()))
			result.rejectValue("password", "Matches", "The passwords must match");
        if(result.hasErrors()){
        	model.addAttribute("userLogin", new LoginUser());
			return LOGINREG;
		}   
		    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashedPassword);
	        this.userService.create(user);
	        session.setAttribute("user", user.getId());
			return FORUM;
		}
	
	@RequestMapping(value="/averageJoeAuto/forum/{id}")
	public String viewThread(@PathVariable("id") Long forumId,
			@ModelAttribute("post") Post post,
			Model model,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		if(userService.validation(session, model, redirectAttributes) != null) {
			return REDIRECT;
		}
		model.addAttribute(threadService.findThreadById(forumId));
		model.addAttribute("posts", postService.findPostsByThread(forumId));
		return "threadViewer.jsp";
	}
	
	@RequestMapping(value="averageJoeAuto/user/{id}")
	public String viewUser(@PathVariable("id") Long userId,
			Model model,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("user", userService.userByID(userId));
		model.addAttribute("posts", postService.findPostsByUserId(userId));
		return "userViewer.jsp";
	}
	
	@GetMapping("/averageJoeAuto/forum")
	public String splashPage(Model model, 
			HttpSession session,
			RedirectAttributes redirectAttributes) {
			if(userService.validation(session, model, redirectAttributes) != null) {
				return REDIRECT;
			}
		Long user = (Long) session.getAttribute("user");
		model.addAttribute("user", userService.userByID(user));
		model.addAttribute("threads", threadService.allThreads());
		return "splash.jsp";
		
	}
	

}
