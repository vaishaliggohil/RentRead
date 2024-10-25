package com.project.rentread.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.rentread.config.SecurityConfig;
import com.project.rentread.entites.Role;
import com.project.rentread.entites.User;
import com.project.rentread.models.UserRegisterRequest;
import com.project.rentread.models.UserResponse;
import com.project.rentread.repositories.UserRepository;

import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
@Mock
    private UserRepository userRepository;

    @Mock
    private SecurityConfig securityConfig;

    @InjectMocks
    private UserService userService;

    private UserRegisterRequest userRegisterRequest;
    private User user;
    private UserResponse userResponse;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("testuser");
        userRegisterRequest.setPassword("password");
        userRegisterRequest.setFirstName("name");

        user = new User();
        user.setId(1L);
        user.setEmail("testuser");
        user.setPassword("encryptedPassword");
        user.setRoles(new HashSet<>());

        userResponse = new UserResponse();
        userResponse.setEmail("testuser");

        passwordEncoder = mock(PasswordEncoder.class);
        when(securityConfig.passwordEncoder()).thenReturn(passwordEncoder);
        when(passwordEncoder.encode(any(String.class))).thenReturn("encryptedPassword");
    }

    @Test
    void testRegisterUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.registerUser(userRegisterRequest, Role.USER);

        assertNotNull(response);
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getFirstName(), response.getFirstName());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(userRegisterRequest.getPassword());
    }

    @Test
    void testRegisterAdmin() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.registerUser(userRegisterRequest, Role.ADMIN);

        assertNotNull(response);
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getFirstName(), response.getFirstName());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(userRegisterRequest.getPassword());
    }
}
