package dto;

import models.Stream;

public record StreamResponseDTO(
        String nome,
        String nomeUsuario,
        Float custoStream
) {
    public static StreamResponseDTO valueOf(Stream stream){
        return new StreamResponseDTO(
                stream.getNome(),
                stream.getNomeUsuario(),
                stream.getCustoStream()
        );
    }
}
