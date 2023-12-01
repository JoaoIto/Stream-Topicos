package repositorys;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Game;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game>{
    /*public List<GameRepository> findByNome(String nome){
        return find("nickname LIKE ?1", "%"+nome+"%").list();
    }
*/
}
