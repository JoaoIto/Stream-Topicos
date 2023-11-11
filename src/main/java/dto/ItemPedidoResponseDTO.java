package dto;

import models.ItemPedido;

import java.util.List;

public record ItemPedidoResponseDTO(
        Integer quantidade,
        Double preco,
        Long idProduto,
        String nome
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item){
        return new ItemPedidoResponseDTO(
                item.getQuantidade(),
                item.getPreco(),
                item.getDuo().getId(),
                item.getDuo().getAnnotation());
    }

    public static List<ItemPedidoResponseDTO> valueOf(List<ItemPedido> item) {
        return item.stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList();
    }

}