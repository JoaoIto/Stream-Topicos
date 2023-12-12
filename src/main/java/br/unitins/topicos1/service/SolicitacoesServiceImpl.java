package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.SolicitacaoRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class SolicitacoesServiceImpl implements SolicitacoesService {

    @Inject
    SolicitacaoRepository repository;

    @Inject
    DuoService duoService;

    @Inject
    SecurityIdentity securityIdentity;


    @Transactional
    public SolicitacaoResponseDTO criarSolicitacao(Duo duo, Usuario usuario) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setUsuario(usuario);
        solicitacao.setDuo(duo);

        // Cálculo do valor total (adicione a lógica conforme necessário)
        Float valorTotal = calcularValorTotal(duo);
        solicitacao.setValorTotal(valorTotal);

        // Defina o status inicial da solicitação (pode ser AGUARDANDO ou outro status desejado)
        solicitacao.setStatus(StatusSolicitacao.AGUARDANDO);

        // Persiste a solicitação no banco de dados
        repository.persist(solicitacao);

        return SolicitacaoResponseDTO.valueOf(solicitacao);
    }

    private Float calcularValorTotal(Duo duo) {
        // Adicione a lógica de cálculo conforme necessário
        // Este é um exemplo simples, você precisa implementar sua lógica específica
        return duo.getQuantidadeHoras() * duo.getStream().getPrecoStream();
    }


    @Override
    public List<SolicitacaoResponseDTO> findAll() {
        String login = securityIdentity.getPrincipal().getName();
        List<Solicitacao> solicitacaos = repository.findAll(login);
        return solicitacaos.stream()
                .map(SolicitacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Solicitacao findById(Long id) {
        return null;
    }
}
