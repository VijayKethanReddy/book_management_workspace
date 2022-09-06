package com.book.service;

import javax.validation.Valid;
import com.book.entity.Author;

/**
 * 
 * @author cogjava3180
 * This is AuthorService interface which used for defining author details methods
 *
 */

public interface AuthorService {

	public Author getAuthor(int authorId);

	public Author saveAuthor(@Valid Author author);

}
