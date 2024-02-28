package br.unitins.topicos1.services.Solicitacoes;

import br.unitins.topicos1.dto.Solicitacao.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Solicitacao.Solicitacao;
import br.unitins.topicos1.model.Solicitacao.StatusSolicitacao;
import br.unitins.topicos1.model.Usuario.Usuario;
import br.unitins.topicos1.repositories.PagamentoRepository;
import br.unitins.topicos1.repositories.SolicitacaoRepository;
import br.unitins.topicos1.repositories.UsuarioRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SolicitacoesServiceImpl implements SolicitacoesService {

    @Inject
    SolicitacaoRepository repository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    UsuarioRepository usuarioRepository;
    /*
    @Inject
    BoletoRepository boletoRepository;
    
    @Inject
    PixRepository pixRepository;

    @Inject
    CartaoCreditoRepository cartaoCreditoRepository;
    */

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
    public SolicitacaoResponseDTO findById(Long id) {
        return SolicitacaoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public SolicitacaoResponseDTO atualizarSolicitacao(Long id, Long idPagamento){
        Solicitacao solicitacao = repository.findById(id);
        Pagamento pagamento = pagamentoRepository.findById(idPagamento);

        solicitacao.setPagamento(pagamento);
        repository.persist(solicitacao);
        return SolicitacaoResponseDTO.valueOf(solicitacao);
    }
}
