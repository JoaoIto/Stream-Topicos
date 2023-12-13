package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.SolicitacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService{

    @Inject
    PagamentoRepository repository;

    @Inject
    SolicitacaoRepository solicitacaoRepository;

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
