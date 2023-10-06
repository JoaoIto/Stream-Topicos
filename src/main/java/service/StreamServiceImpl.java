package service;

import dto.StreamDTO;
import dto.StreamResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import models.Stream;
import repositorys.StreamRepository;
import resources.StreamResource;

import java.util.List;

@ApplicationScoped
public class StreamServiceImpl implements StreamService {
    @Inject
    StreamRepository repository;

    @Override
    public StreamResponseDTO insert(StreamDTO dto) {
        Stream stream = new Stream();
        stream.setNome(dto.getNome());
        stream.setNomeUsuario(dto.getNomeUsuario());
        stream.setCustoStream(dto.getCustoStream());
        repository.persist(stream);
        return StreamResponseDTO.valueOf(stream);
    }

    @Override
    public StreamResponseDTO update(StreamDTO dto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<StreamResponseDTO> findAll() {
        return repository.findAll().stream().map(StreamResponseDTO::valueOf).toList();
    }

    @Override
    public Stream findById(Long id) {
        return repository.findById(id);
    }


}
