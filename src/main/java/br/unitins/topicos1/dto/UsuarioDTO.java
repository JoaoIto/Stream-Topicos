package br.unitins.topicos1.dto;

import java.util.List;

public record UsuarioDTO (
    String nome,
    String login,
    String senha,
    List<TelefoneDTO> listaTelefone
) {

}
