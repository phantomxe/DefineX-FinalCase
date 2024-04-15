package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.User;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { 
    public Optional<User> findByEmail(String email); 
}
