package com.project.rentread.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentread.entites.Rental;
import com.project.rentread.services.RentalService;

@RestController
@RequestMapping("/rent")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PutMapping ("/borrow/{bookId}")
    public ResponseEntity<Rental> borrowBook(@PathVariable(value = "bookId") Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(rentalService.rentBook(authentication.getName(), bookId));
    }

    @PutMapping("/return/{rentalId}")
    public ResponseEntity<Void> returnBook(@PathVariable(value = "rentalId") Long rentalId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        rentalService.returnBook(authentication.getName(), rentalId);
        return ResponseEntity.ok().build();
    }

}
