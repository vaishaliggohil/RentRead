package com.project.rentread.repositories;

import org.springframework.stereotype.Repository;
import com.project.rentread.entites.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByAvailableTrue();
}
