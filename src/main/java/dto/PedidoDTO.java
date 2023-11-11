package dto;

import java.util.List;

public record PedidoDTO (
        Long idUsuario,
        List<ItemPedidoDTO> games
) {

}