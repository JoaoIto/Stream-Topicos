package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.dto.StreamResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface StreamService {
    StreamResponseDTO insert(@Valid StreamDTO dto);
    StreamResponseDTO update(StreamDTO dto, Long id);
    void delete(Long id);
    StreamResponseDTO findById(Long id);
    List<StreamResponseDTO> findByNome(String nome);
    List<StreamResponseDTO> findAll();
}
