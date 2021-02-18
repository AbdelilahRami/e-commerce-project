package com.shopping.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shopping.application.models.User;
import com.shopping.application.repositorie.UserRepository;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    
    public List<User> getAll(){
        return userRepository.getAll();
    }
    
    public User getById(String id) {
        return userRepository.getById(id);
    }
    
    public User createUser(User user) {
        return userRepository.saveOrUpdate(user);
    }
    
    public User updateUser(User user) {
        return userRepository.saveOrUpdate(user);
    }
    
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
