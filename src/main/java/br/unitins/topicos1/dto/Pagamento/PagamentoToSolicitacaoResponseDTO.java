package br.unitins.topicos1.dto.Pagamento;

import br.unitins.topicos1.model.Pagamento.TipoPagamento;
import br.unitins.topicos1.model.Solicitacao.Solicitacao;

import java.time.LocalDate;

public record PagamentoToSolicitacaoResponseDTO(
        TipoPagamento tipoPagamento,
        LocalDate dataPagamento
) {
    public static PagamentoToSolicitacaoResponseDTO valueOf(Solicitacao solicitacao) {
        return new PagamentoToSolicitacaoResponseDTO(
                solicitacao.getPagamento() != null ? solicitacao.getPagamento().getTipoPagamento() : null,
                solicitacao.getPagamento() != null ? solicitacao.getPagamento().getDataConfirmacaoPagamento() : null
        );
    }

}
