package com.springboot.todoapp.repository.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
