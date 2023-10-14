package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {

    public EstadoResponseDTO insert(@Valid EstadoDTO dto);

    public EstadoResponseDTO update(EstadoDTO dto, Long id);

    public void delete(Long id);

    public EstadoResponseDTO findById(Long id);

    public List<EstadoResponseDTO> findByNome(String nome);

    public List<EstadoResponseDTO> findByAll(); 
    
}
