package service;

import java.util.List;

import dto.GameDTO;
import dto.GameResponseDTO;
import jakarta.ws.rs.PathParam;
import models.Game;

public interface GameService {
    
    public GameResponseDTO insert(GameDTO dto);

    public GameResponseDTO update(GameDTO dto, Long id);

    public void delete(Long id);

    public List<GameResponseDTO> findAll();

    public Game findById(@PathParam("id") Long id);

    public List<GameResponseDTO> findByNome(@PathParam("nome") String nome);

}
