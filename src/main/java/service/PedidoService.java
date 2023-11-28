// PedidoService.java
package service;

import dto.PedidoDTO;
import dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {
    PedidoResponseDTO insert(PedidoDTO dto);

    List<PedidoResponseDTO> findAll();
}
