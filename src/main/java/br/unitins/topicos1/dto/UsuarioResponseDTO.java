package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login,
    List<TelefoneDTO> listaTelefone
) { 
    public static UsuarioResponseDTO valueOf(Usuario usuario){

        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getListaTelefone()
                .stream()
                .map(t -> TelefoneDTO.valueOf(t)).toList()
        );
    }
}
