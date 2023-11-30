package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Stream;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

import java.util.List;

@ApplicationScoped
public class StreamRepository implements PanacheRepository<Stream> {
    public List<Stream> findByNome(String nome) {
        try {
            return find("nome = ?1 ", nome ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
