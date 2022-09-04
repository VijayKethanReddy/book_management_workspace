package com.email.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {
	
	@PostMapping
	public String sendEmail(@RequestBody String emailId) {
		log.debug("sent email to "+ emailId);
		return "email sent";
	}

}
