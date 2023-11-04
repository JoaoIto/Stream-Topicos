package br.unitins.topicos1.dto;


import java.util.List;

public record PedidoDTO (
    // FormaPagamento pagamento,
    // EnderecoDTO endereco,
    List<ItemPedidoDTO> itens
) {

}
