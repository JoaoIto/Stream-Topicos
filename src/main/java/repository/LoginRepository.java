package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Login;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<Login> {
    public List<Login> findByCadastro(String nickname) {
        return list("login.cadastro.nickname", nickname);
    }


    public Login findByLoginAndSenha(String senha) {
        return find("senha = ?1", senha).firstResult();
    }
}
