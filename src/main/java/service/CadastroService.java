package service;

import dto.CadastroDto;
import dto.CadastroResponseDTO;
import jakarta.ws.rs.PathParam;
import models.cadastro.Cadastro;

import java.util.List;

public interface CadastroService {
    public CadastroResponseDTO insert(CadastroDto dto);

    public CadastroResponseDTO update(CadastroDto dto, Long id);

    public void delete(Long id);

    public List<CadastroResponseDTO> findAll();

    public List<CadastroResponseDTO> findByNick(@PathParam("nickname") String nickname);

    public Cadastro findById(@PathParam("id") Long id);
}