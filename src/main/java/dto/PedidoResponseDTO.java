package dto;

import models.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        CadastroResponseDTO usuario,
        Double totalPedido,
        List<ItemPedidoResponseDTO> itens
) {
    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                CadastroResponseDTO.valueOf(pedido.getUsuario()),
                pedido.getTotalPedido(),
                ItemPedidoResponseDTO.valueOf(pedido.getItens()));
    }
}