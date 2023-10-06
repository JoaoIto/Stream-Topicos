package repositorys;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Login;

import java.util.List;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<Login> {
    public List<Login> findByNick(String nickname){
        return find("nickname LIKE ?1", "%"+nickname+"%").list();
    }
}
