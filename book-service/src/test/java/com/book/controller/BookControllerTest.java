package com.book.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.book.constants.BookConstants;
import com.book.entity.Author;
import com.book.entity.Book;
import com.book.service.AuthorService;
import com.book.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
	@Mock
	BookService bookService;
	
	@Mock
	AuthorService authorService;
	
	@InjectMocks
	BookController controller;
	
	@Mock
	RestTemplate restTemplate;
	
	Author getAuthor() {
		Author author = new Author();
		author.setAuthorId(1);
		author.setName("David");
		author.setEmailId("david@gmail.com");
		author.setUserName("David1");
		return author;
	}
	
	Book getBook() {
		Book book = new Book();
		book.setBookId(1);
		book.setLogo("book1.url");
		book.setTitle("book1");
		book.setCategory("Adventure");
		book.setPrice(1);
		book.setAuthor(getAuthor());
		book.setPublisher("ABC Publisher");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate publishedDate = LocalDate.parse("01/09/2022", formatter);
		book.setPublishedDate(publishedDate );
		book.setContent("This is book1 content");
		book.setActive(true);
		return book;
	}

	@Test
	void testSaveBook() {
		Author author = getAuthor();
		Book book = getBook();
		int authorId = 1;
		ResponseEntity<String> response = new ResponseEntity<String>("email sent", HttpStatus.OK);
		when(authorService.getAuthor(authorId)).thenReturn(author);
		when(bookService.saveBook(book)).thenReturn(book);
		when(restTemplate.postForEntity(BookConstants.SEND_EMAIL_URL, author.getEmailId(), String.class)).thenReturn(response);
		ResponseEntity<Integer> savedbookId = controller.saveBook(1, book);
		assertEquals(1, savedbookId.getBody());
	}
	
	@Test
	void testSaveBook1() {
		Author author = null;
		Book book = getBook();
		book.setAuthor(author);
		int authorId = 2;
		when(authorService.getAuthor(authorId)).thenReturn(author);
		ResponseEntity<Integer> savedbookId = controller.saveBook(2, book);
		assertEquals(HttpStatus.BAD_REQUEST, savedbookId.getStatusCode());
	}

	@Test
	void testSearchBooks() {
		Book book = getBook();
		List<Book> books = new ArrayList<>();
		books.add(book);
		String category="Adventure";
		String author="David";
		int price=1;
		String publisher="ABC Publisher";
		when(bookService.searchBooks(category, author, price, publisher)).thenReturn(books);
		ResponseEntity<List<Book>> actual= controller.searchBooks(category, author, price, publisher);
		assertEquals(books, actual.getBody());
	}
	
	@Test
	void testGetBook() {
		Book book = getBook();
		String emailId="abc@gmail.com";
		Integer bookId=1;
		when(bookService.getBook(bookId)).thenReturn(book);
		ResponseEntity<Book> actual= controller.getBook(emailId, bookId);
		assertEquals(book, actual.getBody());
	}
	
	@Test
	void testGetBook1() {
		Book book = null;
		String emailId="abc@gmail.com";
		Integer bookId=2;
		when(bookService.getBook(bookId)).thenReturn(book);
		ResponseEntity<Book> actual= controller.getBook(emailId, bookId);
		assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
	}
	
	@Test
	void testSaveAuthor() {
		Author author = getAuthor();
		when(authorService.saveAuthor(author)).thenReturn(author);
		ResponseEntity<Integer> savedauthorId = controller.saveAuthor(author);
		assertEquals(1, savedauthorId.getBody());
	}
	
	@Test
	void testSaveAuthor1() {
		Author author = null;
		when(authorService.saveAuthor(author)).thenReturn(author);
		ResponseEntity<Integer> savedauthorId = controller.saveAuthor(author);
		assertEquals(HttpStatus.BAD_REQUEST, savedauthorId.getStatusCode());
	}
}
