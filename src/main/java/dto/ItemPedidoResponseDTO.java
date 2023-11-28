package dto;

import models.Duo;
import models.ItemPedido;

public record ItemPedidoResponseDTO(
        Integer quantidade,
        Double preco,
        Duo duo
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
                item.getQuantidade(),
                item.getPreco(),
                item.getDuo()
        );
    }
}
