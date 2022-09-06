package com.book.service.impl;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.AuthorRepository;
import com.book.entity.Author;
import com.book.service.AuthorService;

/**
 * 
 * @author cogjava3180
 * This is AuthorServiceImpl which is used for running methods from controller
 * getAuthor method is used for fetching author details for author id
 * saveAuthor method is used for saving author details
 *
 */

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public Author getAuthor(int authorId) {
		Author author = null;
		Optional<Author> authorOptional = authorRepository.findById(authorId);
		if(authorOptional.isPresent()) {
			author = authorOptional.get();
		}
		return author;
	}
	
	@Override
	public Author saveAuthor(@Valid Author author) {
		return authorRepository.save(author);
	}

}
