package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    String login,
    String senha,
    Integer idPerfil,
    List<TelefoneDTO> listaTelefone
) {

}
