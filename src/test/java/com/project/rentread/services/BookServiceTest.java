package com.project.rentread.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.rentread.entites.Book;
import com.project.rentread.models.BookCreationRequest;
import com.project.rentread.repositories.BookRepository;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository booksRepository;

    @InjectMocks
    private BookService bookService;

    private BookCreationRequest bookCreationRequest;
    private Book book;

    @BeforeEach
    void setUp() {
        bookCreationRequest = new BookCreationRequest();
        bookCreationRequest.setTitle("Test Book");
        bookCreationRequest.setAuthor("Test Author");

        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setAvailable(true);
    }

    @Test
    void testCreateBook() {
        when(booksRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(bookCreationRequest);

        assertNotNull(createdBook);
        assertEquals(book.getTitle(), createdBook.getTitle());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
        assertTrue(createdBook.isAvailable());
        verify(booksRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testDeleteBook() {
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(booksRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook_NotAvailable() {
        book.setAvailable(false);
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(booksRepository, never()).deleteById(1L);
    }

    @Test
    void testGetAllBooks() {
        when(booksRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<Book> books = bookService.getAllBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals(book.getTitle(), books.get(0).getTitle());
        verify(booksRepository, times(1)).findAll();
    }

    @Test
    void testGetAvailableBooks() {
        when(booksRepository.findByAvailableTrue()).thenReturn(Collections.singletonList(book));

        List<Book> books = bookService.getAvailableBooks();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertTrue(books.get(0).isAvailable());
        verify(booksRepository, times(1)).findByAvailableTrue();
    }
}
