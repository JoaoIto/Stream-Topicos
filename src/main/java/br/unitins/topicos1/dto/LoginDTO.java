package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO (
    @NotEmpty(message = "O campo nome não pode ser nulo.")
    String login,
    @NotEmpty(message = "O campo nome não pode ser nulo.")
    String senha
) {

}
