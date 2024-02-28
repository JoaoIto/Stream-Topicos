package br.unitins.topicos1.services.Stream;

import br.unitins.topicos1.dto.Stream.StreamDTO;
import br.unitins.topicos1.dto.Stream.StreamResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface StreamService {
    StreamResponseDTO insert(String login, @Valid StreamDTO dto);
    StreamResponseDTO update(String login, StreamDTO dto, Long id);
    void delete(Long id);
    StreamResponseDTO findById(Long id);
    List<StreamResponseDTO> findByNome(String nome);
    List<StreamResponseDTO> findAll();
}
