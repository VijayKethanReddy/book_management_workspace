package com.book;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
