package com.kafka.model;

import lombok.Data;

/**
 * 
 * @author cogjava3180
 * Author model is used for receiving the author details from Book Controller for sending email
 *
 */

@Data
public class Author {
	
	private int id;
	
	private String name;
	
	private String emailId;
	
	private String userName;
	
	private String password;
}
