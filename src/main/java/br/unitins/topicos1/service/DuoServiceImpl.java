package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoDTO;
import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Game;
import br.unitins.topicos1.model.Stream;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.DuoRepository;
import br.unitins.topicos1.repository.GameRepository;
import br.unitins.topicos1.repository.StreamRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DuoServiceImpl implements DuoService{
    @Inject
    DuoRepository repository;

    @Inject
    StreamRepository streamRepository;

    @Inject
    GameRepository gameRepository;

    @Override
    @Transactional
    public DuoResponseDTO insert(DuoDTO dto)  {
        Duo novoDuo = new Duo();

        novoDuo.setQuantidadeHoras(dto.getQuantidadeHoras());
        List<Game> listGames = gameRepository.findByIdIfExists(dto.getIdGames());
        if(listGames.size() ==  dto.getIdGames().size()){
            novoDuo.setListaGame(listGames);
        }else{
            throw new ValidationException("Game", "O game não foi encontrado");
        }

        if (dto.getIdStream() != null) {
            Stream stream = streamRepository.findById(dto.getIdStream());

            if (stream != null) {
                novoDuo.setStream(stream);
            } else {
                // Lide com a situação em que o Stream não foi encontrado
                throw new ValidationException( "Stream","Stream não encontrado com ID: " + dto.getIdStream());
            }
        }
        repository.persist(novoDuo);
        return DuoResponseDTO.valueOf(novoDuo);
    }

    @Override
    public DuoResponseDTO update(DuoDTO dto, Long id) {
        Duo duo = repository.findById(id);

        if (duo == null) {
            // Lide com a situação em que o Duo não foi encontrado
            throw new EntityNotFoundException("Duo não encontrado com ID: " + id);
        }

        // Atualize os campos do Duo com base nos dados do DTO
        duo.setQuantidadeHoras(dto.getQuantidadeHoras());

        // Atualize a lista de games
        List<Game> listGames = gameRepository.findByIdIfExists(dto.getIdGames());
        if (listGames.size() == dto.getIdGames().size()) {
            duo.setListaGame(listGames);
        } else {
            throw new ValidationException("Game", "O game não foi encontrado");
        }

        // Atualize o Stream, se o ID do Stream for fornecido
        if (dto.getIdStream() != null) {
            Stream stream = streamRepository.findById(dto.getIdStream());

            if (stream != null) {
                duo.setStream(stream);
            } else {
                // Lide com a situação em que o Stream não foi encontrado
                throw new ValidationException("Stream", "Stream não encontrado com ID: " + dto.getIdStream());
            }
        }

        // Salve as alterações no repositório
        repository.persist(duo);

        return DuoResponseDTO.valueOf(duo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Duo duo = repository.findById(id);

        if (duo == null) {
            // Lide com a situação em que o Duo não foi encontrado
            throw new EntityNotFoundException("Duo não encontrado com ID: " + id);
        }

        // Remova o Duo do repositório
        repository.delete(duo);
    }

    @Override
    public List<DuoResponseDTO> findAll() {
        List<Duo> duos = repository.listAll();
        return duos.stream()
                .map(DuoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Duo findById(Long id) {
        return null;
    }
}
