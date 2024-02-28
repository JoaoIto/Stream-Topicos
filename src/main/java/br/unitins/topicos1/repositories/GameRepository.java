package br.unitins.topicos1.repositories;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.model.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GameRepository implements PanacheRepository<Game>{
    /*public List<GameRepository> findByNome(String nome){
        return find("nickname LIKE ?1", "%"+nome+"%").list();
    }

*/
    public List<Game> findByIdIfExists( List<Long> idGames) {
        List<Game> listGames =  new ArrayList<>();
        for(Long id : idGames){
            Game game = this.findById(id);
            if (game != null){
                listGames.add(game);
            }
        }
        return listGames;
    }
}