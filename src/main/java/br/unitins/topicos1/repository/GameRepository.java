package br.unitins.topicos1.repository;

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
}