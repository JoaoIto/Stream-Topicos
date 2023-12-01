package br.unitins.topicos1.dto;
import java.util.List;

import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Game;

public record DuoResponseDTO(
    Long id,
    List<Game> listaGame,
    StreamResponseDTO stream,
    Integer quantidadeHoras

) {
    public static DuoResponseDTO valueOf(Duo duo){
        return new DuoResponseDTO(
                duo.getId(),
                duo.getListaGame(),
                StreamResponseDTO.valueOf(duo.getStream()),
                duo.getQuantidadeHoras()
        );
    }

}
