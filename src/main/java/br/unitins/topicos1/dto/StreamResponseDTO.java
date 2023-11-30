package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Stream;
import br.unitins.topicos1.model.Usuario;

public record StreamResponseDTO(
        Long id,
        String nome,
        Usuario nomeUsuario
) {
    public static StreamResponseDTO valueOf(Stream stream){
        return new StreamResponseDTO(
                stream.getId(),
                stream.getNome(),
                stream.getNomeUsuario()
        );
    }
}