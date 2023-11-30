package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO (
        LocalDateTime dataHora,
        UsuarioResponseDTO usuario,
        List<DuoDTO> duo,
        Integer horasJogadas,
        Double valorHoras,
        Double valorTotal
) {
    public static PedidoDTO valueOf(Pedido pedido) {
        return new PedidoDTO(
                pedido.getDataHora(),
                UsuarioResponseDTO.valueOf(pedido.getUsuario()),
                DuoDTO.valueOfList(pedido.getDuo()), // Corrigido aqui
                pedido.getHorasJogadas(),
                pedido.getValorHoras(),
                pedido.getValorTotal()
        );
    }
}
