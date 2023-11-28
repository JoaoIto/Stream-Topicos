package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import models.Cadastro;

import java.util.List;

@ApplicationScoped
public class CadastroRepository implements PanacheRepository<Cadastro> {
    public List<Cadastro> findByNick(String nickname){
        return find("nickname LIKE ?1", "%"+nickname+"%").list();
    }

    public Cadastro findByLogin(String login) {
        try {
            return find("login = ?1 ", login ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
