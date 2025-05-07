package org.example.learningprogramming.service;

import org.example.learningprogramming.model.User;
import org.example.learningprogramming.model.dto.auth.AuthResponse;
import org.example.learningprogramming.model.dto.user.UpdateUserRequestDTO;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    AuthResponse userRegister(String userEmail, String userPassword);
    AuthResponse userLogin(String userEmail, String userPassword);
    User updateUser(UpdateUserRequestDTO user);
}
