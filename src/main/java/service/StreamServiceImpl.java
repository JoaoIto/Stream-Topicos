package service;

import dto.StreamDTO;
import dto.StreamResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import models.Stream;
import repository.StreamRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StreamServiceImpl implements StreamService {
    @Inject
    StreamRepository repository;

    @Override
    public StreamResponseDTO insert(StreamDTO dto) {
        Stream stream = new Stream();
        stream.setNome(dto.nome());
        stream.setNomeUsuario(dto.nomeUsuario());
        stream.setCustoStream(dto.custoStream());
        repository.persist(stream);
        return StreamResponseDTO.valueOf(stream);
    }

    @Override
    @Transactional
    public StreamResponseDTO update(StreamDTO dto, Long id) {
        Stream stream = repository.findById(id);
        if (stream == null) {
            // Lidar com o caso em que o Stream não foi encontrado, por exemplo, lançar uma exceção
            // ou retornar uma resposta apropriada.
        }

        // Atualize os campos do Stream com base nos dados do DTO
        stream.setNome(dto.nome());
        stream.setNomeUsuario(dto.nomeUsuario());
        stream.setCustoStream(dto.custoStream());
        // Outros campos a serem atualizados

        // Salve as alterações no repositório
        repository.persist(stream);

        // Crie um StreamResponseDTO a partir do Stream atualizado
        StreamResponseDTO responseDTO = new StreamResponseDTO(stream.getId(), stream.getNome(), stream.getNomeUsuario(), stream.getCustoStream());
        return responseDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Stream stream = repository.findById(id);
        if (stream != null) {
            // Remova o Stream do repositório
            repository.delete(stream);
        } else {
            throw new NotFoundException("Stream não encontrado!");
        }
    }

    @Override
    public List<StreamResponseDTO> findAll() {
        return repository.findAll().stream().map(StreamResponseDTO::valueOf).toList();
    }

    @Override
    public Stream findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<StreamResponseDTO> findByNome(String nome) {
        List<Stream> streams = repository.find("nome", nome).list();
        return streams.stream()
                .map(StreamResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<StreamResponseDTO> findByCusto(Float custo) {
        List<Stream> streams = repository.find("custoStream", custo).list();
        return streams.stream()
                .map(StreamResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}

