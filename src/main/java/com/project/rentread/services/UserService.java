package com.project.rentread.services;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.rentread.config.SecurityConfig;
import com.project.rentread.entites.Role;
import com.project.rentread.entites.User;
import com.project.rentread.models.UserRegisterRequest;
import com.project.rentread.models.UserResponse;
import com.project.rentread.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private SecurityConfig securityConfig;

    public UserResponse registerUser(UserRegisterRequest user, Role role){
        ModelMapper modelMapper = new ModelMapper();

        User entity = modelMapper.map(user, User.class);
        entity.setRoles(new HashSet<>());
        entity.getRoles().add(role);
        String encryptedPassword =  securityConfig.passwordEncoder().encode(user.getPassword());
        entity.setPassword(encryptedPassword);
        User response = repository.save(entity);
        return modelMapper.map(response, UserResponse.class);
    }
}
