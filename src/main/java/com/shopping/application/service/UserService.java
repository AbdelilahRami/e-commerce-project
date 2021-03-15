package com.shopping.application.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shopping.application.exception.UserNotFoundException;
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
    
    public User getById(String id) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(UserNotFoundException::new);
    }
    
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    @Transactional
    public User updateUser(User user) throws UserNotFoundException {
        userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }
    
    @Transactional
    public void deleteUser(User user) throws UserNotFoundException {
        userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
