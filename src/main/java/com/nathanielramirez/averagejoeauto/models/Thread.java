package com.nathanielramirez.averagejoeauto.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="threads")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Title is required")
    private String title;
    
    @NotEmpty(message="Post cannot be empty")
    private String content;
    
    public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Thread() {/* Empty Constructor */}


	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    @OneToMany(mappedBy="thread", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts;
   

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

	
    public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
    

}
