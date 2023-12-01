package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.dto.StreamResponseDTO;
import br.unitins.topicos1.model.Stream;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.StreamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StreamServiceImpl implements StreamService {

    @Inject
    StreamRepository repository;

    @Override
    @Transactional
    public StreamResponseDTO insert(StreamDTO dto) {
        Stream stream = new Stream();
        stream.setNome(dto.getNome());
        // Verifique se o id do usuário foi fornecido
        if (dto.getIdUsuario() != null) {
            // Crie uma instância parcial de Usuario com apenas o id
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuario());
            stream.setNomeUsuario(usuario);
        }
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
        stream.setNome(dto.getNome());
        if (dto.getIdUsuario() != null) {
            // Crie uma instância parcial de Usuario com apenas o id
            Usuario usuario = new Usuario();
            usuario.setId(dto.getIdUsuario());
            stream.setNomeUsuario(usuario);
        }
        // Outros campos a serem atualizados

        // Salve as alterações no repositório
        repository.persist(stream);

        // Crie um StreamResponseDTO a partir do Stream atualizado
        return StreamResponseDTO.valueOf(stream);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Stream stream = repository.findById(id);
        if (stream != null) {
            // Remova o Stream do repositório
            repository.delete(stream);
        } else {
            // Lidar com o caso em que o Stream não foi encontrado, por exemplo, lançar uma exceção
            // ou retornar uma resposta apropriada.
        }
    }

    @Override
    public StreamResponseDTO findById(Long id) {
        Stream stream = repository.findById(id);
        if (stream != null) {
            // Carregue o usuário associado à stream
            Usuario usuario = stream.getNomeUsuario();
            // Certifique-se de que o usuário seja carregado antes de criar o StreamResponseDTO
            usuario.getListaTelefone();
            return StreamResponseDTO.valueOf(stream);
        }
        return null;
    }


    @Override
    public List<StreamResponseDTO> findByNome(String nome) {
        List<Stream> streams = repository.findByNome(nome);
        return streams.stream()
                .map(StreamResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<StreamResponseDTO> findAll() {
        List<Stream> streams = repository.listAll();
        return streams.stream()
                .map(StreamResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
