package service;

import dto.PedidoDTO;
import dto.PedidoResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Override
    public PedidoResponseDTO insert(PedidoDTO dto) {
        // Implemente a lógica para inserir um pedido e retorne o PedidoResponseDTO correspondente
        return null; // Substitua pelo código real
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        // Implemente a lógica para buscar todos os pedidos e retornar uma lista de PedidoResponseDTO
        return null; // Substitua pelo código real
    }
}
