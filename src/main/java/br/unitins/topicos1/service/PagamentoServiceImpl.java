package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Pagamento.Pagamento;
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
    SolicitacoesService solicitacoesService;
    @Override
    public PagamentoResponseDTO pagarPix(PixDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(TipoPagamento.PIX);
        pagamento.setConfirmacaoPagamento(true);
        SolicitacaoResponseDTO solicitacaoResponseDTO = solicitacoesService.findById(dto.idSolicitacao());
        pagamento.setValor(solicitacaoResponseDTO.valorTotal());
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());

        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public PagamentoResponseDTO pagarCartaoCredito(CartaoCreditoDTO dto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(TipoPagamento.CREDITO);
        pagamento.setConfirmacaoPagamento(true);
        SolicitacaoResponseDTO solicitacaoResponseDTO = solicitacoesService.findById(dto.idSolicitacao());
        pagamento.setValor(solicitacaoResponseDTO.valorTotal());
        pagamento.setDataConfirmacaoPagamento(LocalDate.now());

        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }
}
