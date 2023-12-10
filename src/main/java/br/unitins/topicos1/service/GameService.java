package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.GameDTO;
import br.unitins.topicos1.dto.GameResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Game;
import jakarta.ws.rs.PathParam;


public interface GameService {
    
    public GameResponseDTO insert(GameDTO dto);

    public GameResponseDTO update(GameDTO dto, Long id);

    public void delete(Long id);

    public GameResponseDTO updateNomeImagem(Long id, String nomeImagem) ;

    public GameResponseDTO findById(Long id);


    public List<GameResponseDTO> findAll();

    public List<GameResponseDTO> findByNome(@PathParam("nome") String nome);

}