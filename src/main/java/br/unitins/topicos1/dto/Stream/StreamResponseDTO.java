package br.unitins.topicos1.dto.Stream;

import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Stream;

public record StreamResponseDTO(
        Long id,
        String nome,

        Float custoStream,
        UsuarioResponseDTO nomeUsuario
) {
    public static StreamResponseDTO valueOf(Stream stream) {
        return new StreamResponseDTO(
                stream.getId(),
                stream.getNome(),
                stream.getPrecoStream(),
                UsuarioResponseDTO.valueOf(stream.getNomeUsuario())
        );
    }

}