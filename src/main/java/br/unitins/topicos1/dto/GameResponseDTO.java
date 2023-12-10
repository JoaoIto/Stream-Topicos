package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Game;

public record GameResponseDTO(
    Long id,
    String nome,
    String categoria,
    String nomeImagem

) {
    public static GameResponseDTO valueOf(Game novoGame){
        return new GameResponseDTO(
            novoGame.getId(),
            novoGame.getNome(),
            novoGame.getCategoria(),
            novoGame.getNomeImagem()
        );
    }
}