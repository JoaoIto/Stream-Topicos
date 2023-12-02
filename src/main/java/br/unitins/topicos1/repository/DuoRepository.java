package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Game;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DuoRepository implements PanacheRepository<Duo> {

}
