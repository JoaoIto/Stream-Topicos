package dto;

import java.util.List;

public record PedidoDTO (
        List<ItemPedidoDTO> games
) {

}