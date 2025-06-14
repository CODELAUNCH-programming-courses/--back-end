package org.example.learningprogramming.repository;

import org.example.learningprogramming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    User findByEmail(String email);
}
