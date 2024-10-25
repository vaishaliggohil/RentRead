package com.project.rentread.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentread.entites.Book;
import com.project.rentread.models.BookCreationRequest;
import com.project.rentread.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Book createBook(BookCreationRequest creationRequest){
        Book bookEntity = modelMapper.map(creationRequest, Book.class);
        bookEntity.setAvailable(true);

        return bookRepository.save(bookEntity);
    }

    public void deleteBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent() && book.get().isAvailable()){
            bookRepository.deleteById(id);
        }
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks(){
        return bookRepository.findByAvailableTrue();
    }

}
