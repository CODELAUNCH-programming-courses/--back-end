package org.example.learningprogramming.model.dto.auth;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.learningprogramming.model.Tariff;

@Getter
@Setter
public class RegisterResponse {
    private Long id;
    private String userName;
    private String email;
    private Tariff tariff;
}
