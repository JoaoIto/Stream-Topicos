package dto;

import models.Stream;

public record StreamResponseDTO(

        Long id,
        String nome,
        String nomeUsuario,
        Float custoStream
) {
    public static StreamResponseDTO valueOf(Stream stream){
        return new StreamResponseDTO(
                stream.getId(),
                stream.getNome(),
                stream.getNomeUsuario(),
                stream.getCustoStream()
        );
    }
}
