package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Duo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class DuoRepository implements PanacheRepository<Duo> {
    public Optional<Duo> findByUserId(Long idUsuario) {
        return  find("stream.nomeUsuario.id = ?1", idUsuario).stream().findFirst();
    }
}
