package com.book.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.AuthorRepository;
import com.book.entity.Author;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	public Author getAuthor(int authorId) {
		Author author = null;
		Optional<Author> authorOptional = authorRepository.findById(authorId);
		if(authorOptional.isPresent()) {
			author = authorOptional.get();
		}
		return author;
	}

	public Author saveAuthor(@Valid Author author) {
		return authorRepository.save(author);
	}

}
