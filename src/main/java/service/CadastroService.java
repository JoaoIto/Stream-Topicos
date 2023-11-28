package service;

import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import dto.LoginDTO;
import jakarta.ws.rs.PathParam;
import models.Cadastro;

import java.util.List;

public interface CadastroService {
    public CadastroResponseDTO insert(CadastroDTO dto);

    public CadastroResponseDTO update(CadastroDTO dto, Long id);

    public CadastroResponseDTO updateNomeImagem(Long id, String nomeImagem) ;

    public void delete(Long id);

    public List<CadastroResponseDTO> findAll();

    public List<CadastroResponseDTO> findByNick(@PathParam("nickname") String nickname);

    public Cadastro findById(@PathParam("id") String login);
}