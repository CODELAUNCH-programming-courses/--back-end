package org.example.learningprogramming.model.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private Long id;
    private String userName;
    private String email;
}
