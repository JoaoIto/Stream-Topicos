package dto;

public record ItemPedidoDTO (
        Integer quantidade,
        Double preco,
        Long idPedido,
        Long idDuo
){

}