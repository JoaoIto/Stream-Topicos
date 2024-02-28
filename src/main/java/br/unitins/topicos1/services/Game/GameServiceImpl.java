package br.unitins.topicos1.services.Game;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.Game.GameDTO;
import br.unitins.topicos1.dto.Game.GameResponseDTO;
import br.unitins.topicos1.model.Game;
import br.unitins.topicos1.repositories.GameRepository;


@ApplicationScoped
public class GameServiceImpl implements GameService {
    @Inject
    GameRepository repository;

    @Override
    public GameResponseDTO insert(GameDTO dto){
        Game novoGame = new Game();

        novoGame.setNome(dto.getNome());
        novoGame.setCategoria(dto.getCategoria());

        repository.persist(novoGame);
        return GameResponseDTO.valueOf(novoGame);
    }

    @Override
    @Transactional
    public GameResponseDTO update(GameDTO dto, Long id) {
        Game game = repository.findById(id);
        if (game == null) {
            throw new NotFoundException("game não encontrado!");
        }

        game.setNome(dto.getNome());
        game.setCategoria(dto.getCategoria());

        repository.persist(game);

        GameResponseDTO responseDTO = new GameResponseDTO(game.getId(), game.getNome(), game.getCategoria(), game.getNomeImagem());
        return responseDTO;
    }

        @Override
    @Transactional
    public GameResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Game game = repository.findById(id);
        game.setNomeImagem(nomeImagem);
        return GameResponseDTO.valueOf(game);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Game game = repository.findById(id);
        if (game != null) {
            repository.delete(game);
        } else {
            throw new NotFoundException("Game não encontrado!");
        }
    }

    @Override
    public GameResponseDTO findById(Long id) {
        return GameResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<GameResponseDTO> findAll() {
      return repository.findAll().stream().map(GameResponseDTO::valueOf).toList();
    }

    @Override
    public List<GameResponseDTO> findByNome(String nome) {
        List<Game> game = repository.find("nome", nome).list();
        return game.stream()
                .map(GameResponseDTO::valueOf)
                .collect(Collectors.toList());
    }


}