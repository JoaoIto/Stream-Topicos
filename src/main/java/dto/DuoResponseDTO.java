package dto;

import java.util.List;

import models.Duo;
import models.Game;

public record DuoResponseDTO(
    Long id,
    List<Game> listaGame,
 
    StreamDTO stream

) {
    public static DuoResponseDTO valueOf(Duo duo){
        return new DuoResponseDTO(
                duo.getId(),
                duo.getListaGame(),
                duo.getStream()
        );
    }

}

