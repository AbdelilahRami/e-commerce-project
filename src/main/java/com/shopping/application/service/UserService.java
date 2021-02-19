package com.shopping.application.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shopping.application.models.User;
import com.shopping.application.repositorie.UserRepository;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(User user) {
        return null;
    }
    
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
