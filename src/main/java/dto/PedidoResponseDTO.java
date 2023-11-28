package dto;

import models.ItemPedido;
import models.Pedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        List<ItemPedido> games
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getGames()
        );
    }
}
