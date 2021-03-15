package com.shopping.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shopping.application.exception.UserNotFoundException;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.UserRepository;


public class UserServiceTest {

    private User user;
    private Address address;
    private UserRepository userRepository;
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        address = new Address();
        address.setPostalCode("test");
        address.setAddress("test");
        address.setCity("test");
        
        user = new User();
        user.setId(UUID.fromString("ec91018d-5103-4774-bd0c-f0bd85321f38"));
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("test");
        user.setPhone("test");
        user.setAddress(address);
        
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }
    
    /*--------------------get all--------------------*/
    @Test
    void getAllShouldRetunUserList() {
        
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        
        List<User> results = userService.getAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(user);
    }
    
    /*--------------------getById--------------------*/
    @Test
    void getByIdRetunUser() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";
        
        when(userRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(user));
        
        User result = userService.getById(id);
        assertThat(result).isEqualTo(user);
    }
    
    @Test
    void getByIdShouldThrowsExceptionIfUserNotFoud() {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f40";
        
        when(userRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());
        
        try {
            userService.getById(id);
            assertThat(false);
        } catch (UserNotFoundException e) {
            assertThat(true);
        }

    }
    
    /*--------------------create--------------------*/
    @Test
    void createRetunUser() {
        
        when(userRepository.save(user)).thenReturn(user);
        
        User result = userService.createUser(user);
        assertThat(result).isEqualTo(user);
    }
    
    /*--------------------update--------------------*/
    @Test
    void updateRetunUser() throws UserNotFoundException {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";
        
        when(userRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        
        User result = userService.updateUser(user);
        assertThat(result).isEqualTo(user);
    }
    
    @Test
    void updateShouldThrowsExceptionIfUserNotFoud() throws UserNotFoundException {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";
        
        when(userRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);
        
        try {
            userService.getById(id);
            assertThat(false);
        } catch (UserNotFoundException e) {
            assertThat(true);
        }
    }
}
