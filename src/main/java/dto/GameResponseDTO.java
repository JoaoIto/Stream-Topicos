package dto;

import models.Game;
import repositorys.GameRepository;

public record GameResponseDTO(
    Long id,
    String nome,
    String categoria
) {
    public static GameResponseDTO valueOf(Game novoGame){
        return new GameResponseDTO(
            novoGame.getId(),
            novoGame.getNome(),
            novoGame.getCategoria()
        );
    }
}
