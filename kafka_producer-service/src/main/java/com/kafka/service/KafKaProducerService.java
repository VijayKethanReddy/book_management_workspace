package com.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.kafka.constants.ApplicationConstants;
import com.kafka.model.Book;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafKaProducerService 
{
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(String message) 
	{
		log.info(String.format("Message sent -> %s", message));
		this.kafkaTemplate.send(ApplicationConstants.TOPIC_NAME_TEST, message);
	}
	
	public void sendEmailForBookCreation(Book book) 
	{
		log.info("Book: "+ book.toString());
		this.kafkaTemplate.send(ApplicationConstants.TOPIC_NAME_SEND_EMAIL, book);
	}
}
