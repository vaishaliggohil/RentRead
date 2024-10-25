package com.project.rentread.entites;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rentals")
@Setter
@Getter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id",nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "book_id",nullable = false)
    @JsonManagedReference
    private Book book;

    @Column(nullable = false)
    private LocalDate rentedAt;

    @Column(nullable = true)
    private LocalDate returnedAt;
    
}
