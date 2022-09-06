package com.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.Author;

/**
 * 
 * @author cogjava3180
 * This is AuthorRepository which is used for saving author details and fetching author details from db
 *
 */

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
