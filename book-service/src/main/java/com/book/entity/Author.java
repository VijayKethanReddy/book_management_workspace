package com.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;
	
	@NotBlank(message = "autherName cannot be blank#######")
	private String name;
	
	@Email
	@NotBlank(message = "emailId cannot be blank#######")
	private String emailId;
	
	@NotBlank(message = "username cannot be blank#######")
	private String userName;
	
	@NotBlank(message = "password cannot be blank#######")
	private String password;
}
