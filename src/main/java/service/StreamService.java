package service;

import dto.LoginDTO;
import dto.LoginResponseDTO;
import dto.StreamDTO;
import dto.StreamResponseDTO;
import jakarta.ws.rs.PathParam;
import models.Login;
import models.Stream;

import java.util.List;

public interface StreamService {

    public StreamResponseDTO insert(StreamDTO dto);

    public StreamResponseDTO update(StreamDTO dto, Long id);

    public void delete(Long id);

    public List<StreamResponseDTO> findAll();


    public Stream findById(@PathParam("id") Long id);

    public List<StreamResponseDTO> findByNome(String nome);

    public List<StreamResponseDTO> findByCusto(Float custo);
}
