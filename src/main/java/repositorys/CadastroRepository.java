package repositorys;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.cadastro.Cadastro;

import java.util.List;

@ApplicationScoped
public class CadastroRepository implements PanacheRepository<Cadastro> {
    public List<Cadastro> findByNick(String nickname){
        return find("nickname LIKE ?1", "%"+nickname+"%").list();
    }
}
