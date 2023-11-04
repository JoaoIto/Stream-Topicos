package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import models.Login;

import java.util.Objects;

public record CadastroDTO(
        @NotBlank(message = "O campo não pode ser nulo!")
        String nome,

        @NotBlank(message = "O campo não pode ser nulo!")
        @Email(message = "O email tem que ser válido!")
        String email,

        @NotBlank(message = "O campo não pode ser nulo!")
        String nickname,


        LoginDTO login
) {}
