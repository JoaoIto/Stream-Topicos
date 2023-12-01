package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoDTO;
import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.model.Duo;
import jakarta.ws.rs.PathParam;

import java.util.List;

public interface DuoService {
    public DuoResponseDTO insert(DuoDTO dto) ;

    public DuoResponseDTO update(DuoDTO dto, Long id);

    public void delete(Long id);

    public List<DuoResponseDTO> findAll();

    public Duo findById(@PathParam("id") Long id);
}
