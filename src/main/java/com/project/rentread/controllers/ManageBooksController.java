package com.project.rentread.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.rentread.entites.Book;
import com.project.rentread.services.BookService;
import com.project.rentread.models.BookCreationRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/manage/book")
@Validated
public class ManageBooksController {

    @Autowired
    BookService bookService;

    @PostMapping 
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookCreationRequest creationRequest) {
        return ResponseEntity.ok(bookService.createBook(creationRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
