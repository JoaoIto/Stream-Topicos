package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Game;

import java.util.List;

public record GameDTO(
        Long id,
        String nome,
        String categoria,
        String modalidade
) {

    public static GameDTO valueOf(Game game) {
        return new GameDTO(
                game.getId(),
                game.getNome(),
                game.getCategoria(),
                game.getModalidade()
        );
    }
}
