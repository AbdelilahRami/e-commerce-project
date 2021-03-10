package com.shopping.application.repositorie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shopping.application.models.Address;
import com.shopping.application.models.User;

@DataJpaTest
public class UserRepositoryTest {

    private User user;
    private Address address;
    @Autowired
    private UserRepository userRepository;
    
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
        userRepository.save(user);
    }
    
    @Test
    void getAllUser() {
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }
}
