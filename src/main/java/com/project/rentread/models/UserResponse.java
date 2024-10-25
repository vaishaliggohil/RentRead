package com.project.rentread.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.rentread.entites.Rental;
import com.project.rentread.entites.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;
    Set<Rental> rentals;
    
}
