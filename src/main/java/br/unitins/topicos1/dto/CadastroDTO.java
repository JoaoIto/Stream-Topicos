package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CadastroDTO(
        @NotBlank(message = "O campo nome não pode ser nulo.")
        String nome,
        String login,
        String senha,
        String cpf,

        List<TelefoneDTO> listaTelefone
) {

}
