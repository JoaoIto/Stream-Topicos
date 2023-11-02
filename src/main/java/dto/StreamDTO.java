package dto;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record StreamDTO(
        @NotNull(message = "Não pode ser nulo!")
        String nome,

        @NotNull(message = "Não pode ser nulo!")
        String nomeUsuario,

        @NotNull(message = "Não pode ser nulo!")
        Float custoStream
) {}
