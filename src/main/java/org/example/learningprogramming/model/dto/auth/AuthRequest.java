package org.example.learningprogramming.model.dto.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String password;
    private String email;
}
