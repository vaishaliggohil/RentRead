package com.project.rentread.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.rentread.entites.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
