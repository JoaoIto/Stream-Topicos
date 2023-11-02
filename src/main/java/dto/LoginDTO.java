package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import models.Cadastro;

import java.util.Objects;

public record LoginDTO (
        @NotEmpty(message = "O campo nome não pode ser nulo.")
        String login,
        @NotEmpty(message = "O campo nome não pode ser nulo.")
        String senha
){}
