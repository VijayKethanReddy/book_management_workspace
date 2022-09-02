package com.book.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.book.entity.Author;
import com.book.entity.Book;
import com.book.service.AuthorService;
import com.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/digitalbooks")
public class BookController extends BaseController {
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/readers/{emailId}/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable String emailId, @PathVariable Integer bookId) {
		log.debug("Inside getBook method");
		ResponseEntity<Book> response;
		Book book = bookService.getBook(bookId);
		if(book == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response = new ResponseEntity<>(book, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/books/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam String category, 
			@RequestParam String author, @RequestParam int price, 
			@RequestParam String publisher) {
		ResponseEntity<List<Book>> response;
		List<Book> listOfBooks = bookService.searchBooks(category, author, price, publisher);
		response = new ResponseEntity<>(listOfBooks, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/author/{authorId}/books")
	public ResponseEntity<Integer> saveBook(@PathVariable("authorId") int authorId, @Valid @RequestBody Book book) {
		ResponseEntity<Integer> response;
		Author author = authorService.getAuthor(authorId);
		if(author!=null) {
			book.setAuthor(author);
			bookService.saveBook(book);
			int bookId = book.getBookId();
			response = new ResponseEntity<>(bookId, HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/author/signup")
	public ResponseEntity<Integer> saveAuthor(@Valid @RequestBody Author author) {
		ResponseEntity<Integer> response;
		Author author1 = authorService.saveAuthor(author);
		if(author1!=null) {
			int authorId = author1.getAuthorId();
			response = new ResponseEntity<>(authorId, HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
