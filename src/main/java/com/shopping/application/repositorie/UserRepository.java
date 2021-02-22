package com.shopping.application.repositorie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.application.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAll();
    
    Optional<User> findById(UUID id);
        
}
