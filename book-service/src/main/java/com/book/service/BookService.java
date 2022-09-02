package com.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.BookRepository;
import com.book.entity.Book;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> searchBooks(String category, String author, int price, String publisher) {
		List<Book> listOfBooks = new ArrayList<>();
		List<Book> bookList = bookRepository.findAll();
		if(!bookList.isEmpty()) {
			listOfBooks = bookList.stream().
			filter(book -> book.getCategory().equalsIgnoreCase(category) || 
					book.getAuthor().getName().equalsIgnoreCase(author) || (book.getPrice() == price) 
					|| book.getPublisher().equalsIgnoreCase(publisher)).collect(Collectors.toList());
		}
		return listOfBooks;
	}

	public Book getBook(Integer bookId) {
		Book book = null;
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if(bookOptional.isPresent()) {
			book = bookOptional.get();
		}
		return book;
	}

}
