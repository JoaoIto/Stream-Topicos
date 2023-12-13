package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.GameResponseDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.SolicitacaoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
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
    DuoService duoService;

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

    
    /*
    @Override
    @Transactional
    public void efetuarPagamentoBoleto(Long idUsuario) throws NullPointerException {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);
        
        Solicitacao solicitacao = validar(usuario);

        Boleto pagamento = new Boleto(solicitacao.getValorTotal(), solicitacao.getUsuario());

        boletoRepository.persist(pagamento);

        solicitacao.setPagamento(pagamento);

        if (solicitacao.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(solicitacao.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoPix(Long idUsuario) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);
        
        Solicitacao solicitacao = validar(usuario);

        Pix pagamento = new Pix(compra.getValorTotal(), compra.getUsuario().getNome(), compra.getUsuario().getPessoaFisica().getCpf());

        pixRepository.persist(pagamento);

        compra.setPagamento(pagamento);

        if (compra.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(compra.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO) {
        
        Usuario usuario = usuarioRepository.findById(idUsuario);

        Solicitacao solicitacao = validar(usuario);

        CartaoCredito pagamento = new CartaoCredito(compra.getValorTotal(),
                                            cartaoCreditoDTO.numeroCartao(),
                                            cartaoCreditoDTO.nomeImpressoCartao(),
                                            usuario.getCpf(),
                                            BandeiraCartao.valueOf(cartaoCreditoDTO.bandeiraCartao()));
        
        cartaoCreditoRepository.persist(pagamento);

        compra.setPagamento(pagamento);

        if (compra.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(compra.getId());
    }

    */
}
