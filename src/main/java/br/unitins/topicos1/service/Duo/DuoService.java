package br.unitins.topicos1.service.Duo;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.dto.Duo.DuoResponseDTO;
import br.unitins.topicos1.model.Duo;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Optional;

public interface DuoService {
    public DuoResponseDTO insert(DuoDTO dto, String login) ;

    public DuoResponseDTO update(DuoDTO dto, Long id);

    public void delete(Long id);

    public List<DuoResponseDTO> findAll();

    public Duo findById(@PathParam("id") Long id);

    public Optional<Duo> findByUserId(@PathParam("id") Long id);
}
