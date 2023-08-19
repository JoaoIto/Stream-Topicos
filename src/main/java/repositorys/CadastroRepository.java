package repositorys;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.cadastro.Cadastro;

import java.util.List;

@ApplicationScoped
public class CadastroRepository implements PanacheRepository<Cadastro> {

    public List<Cadastro> findByNome(String nome){
        return find("nome LIKE ?1", "%"+nome+"%").list();
    }
}
