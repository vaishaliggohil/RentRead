package com.project.rentread.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentread.entites.Book;
import com.project.rentread.entites.Rental;
import com.project.rentread.entites.User;
import com.project.rentread.exceptions.ValidationException;
import com.project.rentread.repositories.BookRepository;
import com.project.rentread.repositories.RentalRepository;
import com.project.rentread.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RentalService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @Transactional
    public Rental rentBook(String email, Long bookId) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new ValidationException("User not found");
        }
        User user = userOptional.get();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new ValidationException("Book not found");
        }
        Book book = bookOptional.get();

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for rental");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentedAt(LocalDate.now());

        user.getRentals().add(rental);
        book.setAvailable(false);

        Rental savedRental = rentalRepository.save(rental);

        userRepository.save(user);
        bookRepository.save(book);

        return savedRental;
    }

    @Transactional
    public Rental returnBook(String email, Long rentalId) {
    
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isEmpty()) {
            throw new ValidationException("Rental not found");
        }
        Rental rental = rentalOptional.get();

        if (rental.getReturnedAt() != null) {
            throw new IllegalStateException("Book has already been returned");
        }

        User user = rental.getUser();
        user.getRentals().remove(rental); 

        Book book = rental.getBook();
        book.setAvailable(true);

        rental.setReturnedAt(LocalDate.now());

        userRepository.save(user);
        bookRepository.save(book);
        rentalRepository.delete(rental); 

        return rental;
    }
}
