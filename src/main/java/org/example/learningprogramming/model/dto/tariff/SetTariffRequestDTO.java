package org.example.learningprogramming.model.dto.tariff;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.learningprogramming.model.Tariff;

@Getter
@Setter
public class SetTariffRequestDTO {
    @NotNull
    private Tariff tariff;
}
