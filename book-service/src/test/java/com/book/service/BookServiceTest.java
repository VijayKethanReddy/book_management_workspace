package com.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.book.BookRepository;
import com.book.entity.Author;
import com.book.entity.Book;
import com.book.service.BookService;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	BookRepository bookRepository;
	
	@InjectMocks
	BookService bookService;
	
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
		Book book = getBook();
		when(bookRepository.save(book)).thenReturn(book);
		bookService.saveBook(book);
		assertEquals(1,	book.getBookId());
	}
	
	@Test
	void testGetBook() {
		Integer bookId = 1;
		Optional<Book> book = Optional.of(getBook());
		when(bookRepository.findById(bookId)).thenReturn(book);
		Book actual = bookService.getBook(bookId);
		assertEquals(bookId, actual.getBookId());
	}
	
	@Test
	void testGetBook1() {
		Integer bookId = 2;
		Optional<Book> book = Optional.empty();
		when(bookRepository.findById(bookId)).thenReturn(book);
		Book actual = bookService.getBook(bookId);
		assertEquals(null, actual);
	}
	
	@Test
	void testSearchBooks() {
		List<Book> books = new ArrayList<>();
		Book book = getBook();
		String category="Adventure";
		String author="David";
		int price=1;
		String publisher="ABC Publisher";
		books.add(book);
		when(bookRepository.findAll()).thenReturn(books);
		Iterable<Book> actual = bookService.searchBooks(category, author, price, publisher);
		assertEquals(books,	actual);
	}
	
	@Test
	void testSearchBooks1() {
		Book book = getBook();
		List<Book> books = new ArrayList<>();
		books.add(book);
		Stream<Book> books1 = books.stream();
		String category="Adventure";
		String author="David";
		int price=1;
		String publisher="ABC Publisher";
		when(bookRepository.findAll()).thenReturn(books);
		List<Book> actual = bookService.searchBooks(category, author, price, publisher);
		List<Book> actual1 = bookService.searchBooks("NA", author, price, publisher);
		List<Book> actual2 = bookService.searchBooks("NA", "NA", price, publisher);
		List<Book> actual3 = bookService.searchBooks("NA", "NA", 2, publisher);
		assertEquals(books,	actual);
		assertEquals(books,	actual1);
		assertEquals(books,	actual2);
		assertEquals(books,	actual3);
	}

}
