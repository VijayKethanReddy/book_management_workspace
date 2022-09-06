package com.email.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.email.constants.ApplicationConstants;
import com.kafka.model.Book;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cogjava3180
 * This is KafkaConsumerService which consumes book details and is used for sending email for email id
 *
 */

@Slf4j
@Service
public class KafKaConsumerService 
{

	@KafkaListener(topics = ApplicationConstants.TOPIC_NAME_SEND_EMAIL, groupId = ApplicationConstants.GROUP_ID)
	public void consume(Book book) {
		log.info("received: "+ book.toString());
		log.debug("sent email to "+ book.getAuthor().getEmailId());
	}
}
