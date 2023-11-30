package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        List<DuoResponseDTO> duos,
        UsuarioResponseDTO usuarioChamador,
        Integer horasJogadas,
        Double valorHoras,
        Double valorTotal,
        LocalDateTime dataHora
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                DuoResponseDTO.valueOfList(pedido.getDuo()), // Representação da lista de duos associados
                UsuarioResponseDTO.valueOf(pedido.getUsuario()), // Representação do usuário que chamou o duo
                pedido.getHorasJogadas(),
                pedido.getValorHoras(),
                pedido.getValorTotal(),
                pedido.getDataHora()
        );
    }
}

