package dto;

import models.Cadastro;
import models.ItemPedido;
import models.Pedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        CadastroResponseDTO cadastro,
        List<ItemPedidoResponseDTO> games
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                CadastroResponseDTO.valueOf(pedido.getUsuario()),
                ItemPedidoResponseDTO.valueOf(pedido.getGames())
        );
    }
}
