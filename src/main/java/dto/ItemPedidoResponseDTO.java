package dto;

import models.Duo;
import models.ItemPedido;

import java.util.List;

public record ItemPedidoResponseDTO(
        Integer quantidade,
        Double preco,
        Long idDuo,
        Boolean statusDuo
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
                item.getQuantidade(),
                item.getPreco(),
                item.getDuo().getId(),
                item.getDuo().getStatus()
        );
    }

    public static List<ItemPedidoResponseDTO> valueOf(List<ItemPedido> item) {
        return item.stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList();
    }
}
