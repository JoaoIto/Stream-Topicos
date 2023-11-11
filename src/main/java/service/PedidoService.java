package service;

import dto.CadastroResponseDTO;
import dto.PedidoDTO;
import dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {
    public PedidoResponseDTO insert(PedidoDTO dto);

    public List<PedidoResponseDTO> findAll();
}
