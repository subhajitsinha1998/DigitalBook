package com.subhajit251447.digitalbook.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.subhajit251447.digitalbook.book.exception.NotFoundException;
import com.subhajit251447.digitalbook.book.model.Book;
import com.subhajit251447.digitalbook.book.repository.BookRepository;
import com.subhajit251447.digitalbook.book.service.BookService;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	// testing adding a book
	@Test
	public void addBook() {
		Book book = new Book();
		when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookService.createBook(book));
	}

	// testing getting only unblocked books
	@Test
	public void getBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book());
		books.add(new Book());
		books.get(0).setActive(true);
		books.get(1).setActive(false);
		when(bookRepository.customSearchBooks(null, null, null, null, null)).thenReturn(books);
		assertEquals(1, bookService.getBooks(null, null, null, null, null).size());
	}

	// testing updating an already added book
	@Test
	public void updateBookSuccess() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("updated title");
		when(bookRepository.save(book)).thenReturn(book);
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		assertEquals(book, bookService.updateBook(book.getId(), book));
	}

	// testing updating a book not present
	@Test
	public void updateBookFail() {
		Book book = new Book();
		book.setId(1);
		book.setTitle("updated title");
		when(bookRepository.save(book)).thenReturn(book);
		when(bookRepository.existsById(book.getId())).thenReturn(false);
		assertThrows(
			NotFoundException.class, 
			() -> bookService.updateBook(1, book));
	}

	// tesing blocking a book
	@Test
	public void updateBookStatusBlocked() {
		Book book = new Book();
		book.setId(1);
		book.setActive(false);
		when(bookRepository.save(book)).thenReturn(book);
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
		Book testBook = new Book();
		testBook.setId(1);
		testBook.setActive(true);
		bookService.createBook(testBook);
		assertEquals(false, bookService.updateBookStatus(
			testBook.getId(), true).getActive());
	}

	// tesing unblocking a book
	@Test
	public void updateBookStatusUnblocked() {
		Book book = new Book();
		book.setId(1);
		book.setActive(true);
		when(bookRepository.save(book)).thenReturn(book);
		when(bookRepository.existsById(book.getId())).thenReturn(true);
		when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
		Book testBook = new Book();
		testBook.setId(1);
		testBook.setActive(false);
		bookService.createBook(testBook);
		assertEquals(true, bookService.updateBookStatus(
			testBook.getId(), false).getActive());
	}

}
