package com.shopping.application.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shopping.application.exception.UserNotFound;
import com.shopping.application.models.User;
import com.shopping.application.repositorie.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    public User getById(String id) throws UserNotFound {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(UserNotFound::new);
    }
    
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
