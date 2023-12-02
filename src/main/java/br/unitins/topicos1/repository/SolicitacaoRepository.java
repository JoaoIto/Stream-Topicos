package br.unitins.topicos1.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.topicos1.model.Solicitacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SolicitacaoRepository implements PanacheRepository<Solicitacao> {

    public List<Solicitacao> findAll(String login) {
        return find("usuario.login = ?1", login).list();
    }

    public Optional<Solicitacao> findByDuoId(Long id) {
        return  find("duo.id = ?1", id).stream().findAny();
    }


}
