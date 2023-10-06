package service;

import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import jakarta.ws.rs.PathParam;
import models.Cadastro;

import java.util.List;

public interface CadastroService {
    public CadastroResponseDTO insert(CadastroDTO dto);

    public CadastroResponseDTO update(CadastroDTO dto, Long id);

    public void delete(Long id);

    public List<CadastroResponseDTO> findAll();

    public List<CadastroResponseDTO> findByNick(@PathParam("nickname") String nickname);

    public Cadastro findById(@PathParam("id") Long id);
}