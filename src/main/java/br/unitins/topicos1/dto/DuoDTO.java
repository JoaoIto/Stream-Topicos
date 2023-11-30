package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Duo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record DuoDTO(
        Double valorHoras,
        List<GameDTO> listaGame,
        String descricao,
        Boolean status
) {
    public static DuoDTO valueOf(Duo duo) {
        GameDTO firstGameDTO = duo.getListaGame().isEmpty() ? null : GameDTO.valueOf(duo.getListaGame().get(0));
        return new DuoDTO(
                duo.getValorHoras(),
                Collections.singletonList(firstGameDTO),
                duo.getDescricao(),
                duo.getStatus()
        );
    }

    public static List<DuoDTO> valueOfList(List<Duo> duoList) {
        return duoList.stream()
                .map(DuoDTO::valueOf)
                .collect(Collectors.toList());
    }

}
