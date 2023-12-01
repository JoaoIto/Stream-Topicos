package br.unitins.topicos1.dto;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String login,
    Perfil perfil,
    String nomeImagem,
    List<TelefoneDTO> listaTelefone
) { 
    public static UsuarioResponseDTO valueOf(Usuario usuario){

        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getPerfil(),
            usuario.getNomeImagem(),
                Optional.ofNullable(usuario.getListaTelefone())
                        .map(telefones -> telefones.stream().map(TelefoneDTO::valueOf).toList())
                        .orElse(null)
        );
    }
}