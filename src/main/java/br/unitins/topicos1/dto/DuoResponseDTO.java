package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Duo;

import java.util.List;
import java.util.stream.Collectors;

public record DuoResponseDTO(
        Long id,
        StreamResponseDTO stream,
        Double valorHoras,
        GameDTO gameDTO,
        String descricao,
        Boolean status
) {
    public static DuoResponseDTO valueOf(Duo duo) {
        GameDTO firstGameDTO = duo.getListaGame().isEmpty() ? null : GameDTO.valueOf(duo.getListaGame().get(0));
        return new DuoResponseDTO(
                duo.getId(),
                StreamResponseDTO.valueOf(duo.getStream()),
                duo.getValorHoras(),
                firstGameDTO,
                duo.getDescricao(),
                duo.getStatus()
        );
    }

    public static List<DuoResponseDTO> valueOfList(List<Duo> duoList) {
        return duoList.stream()
                .map(DuoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}
