package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DuoRepository implements PanacheRepository<Duo> {

}
