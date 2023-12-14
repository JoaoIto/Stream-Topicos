package br.unitins.topicos1.service.Pagamento;

import br.unitins.topicos1.dto.Pagamento.CartaoCredito.CartaoCreditoDTO;
import br.unitins.topicos1.dto.Pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.Pagamento.Pix.PixDTO;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Solicitacao.Solicitacao;
import br.unitins.topicos1.model.Pagamento.TipoPagamento;
import br.unitins.topicos1.model.Solicitacao.StatusSolicitacao;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.SolicitacaoRepository;
import br.unitins.topicos1.service.Pagamento.PagamentoService;
import br.unitins.topicos1.service.Solicitacoes.SolicitacoesService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    SolicitacaoRepository solicitacaoRepository;
    @Inject
    SolicitacoesService solicitacoesService;


    @Override
    public PagamentoResponseDTO pagarPix(PixDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(TipoPagamento.PIX);
        pagamento.setConfirmacaoPagamento(true);
        Solicitacao solicitacao = solicitacaoRepository.findById(dto.idSolicitacao());
        if (solicitacao == null) {
            throw new RuntimeException("Solicitação não encontrada");
        }
        pagamento.setValor(solicitacao.getValorTotal());
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());
        pagamento.setSolicitacao(solicitacao);

        solicitacao.setStatus(StatusSolicitacao.ACEITA);
        repository.persist(pagamento);
        solicitacoesService.atualizarSolicitacao(dto.idSolicitacao(), pagamento.getId());
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public PagamentoResponseDTO pagarCartaoCredito(CartaoCreditoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(TipoPagamento.CREDITO);
        pagamento.setConfirmacaoPagamento(true);
        Solicitacao solicitacao = solicitacaoRepository.findById(dto.idSolicitacao());
        if (solicitacao == null) {
            throw new RuntimeException("Solicitação não encontrada");
        }
        pagamento.setValor(solicitacao.getValorTotal());
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());
        pagamento.setSolicitacao(solicitacao);
        solicitacao.setStatus(StatusSolicitacao.ACEITA);
        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }
}
