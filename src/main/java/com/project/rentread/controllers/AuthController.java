package com.project.rentread.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.rentread.entites.Role;
import com.project.rentread.models.UserResponse;
import com.project.rentread.services.UserService;

import jakarta.validation.Valid;

import com.project.rentread.models.UserRegisterRequest;

@RestController
@RequestMapping("/unauth")
@Validated
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest user) {
        return ResponseEntity.ok().body(userService.registerUser(user, Role.USER));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserResponse> registerAdmin(@Valid @RequestBody UserRegisterRequest user) {
        return ResponseEntity.ok().body(userService.registerUser(user, Role.ADMIN));
    }

}
