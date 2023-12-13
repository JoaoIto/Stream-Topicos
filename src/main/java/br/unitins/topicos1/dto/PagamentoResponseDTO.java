package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Game;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.TipoPagamento;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record PagamentoResponseDTO(
        Float valor,
        Boolean confirmacaoPagamento,
        LocalDate dataConfirmacaoPagamento,

        TipoPagamento tipoPagamento,
        SolicitacaoResponseDTO solicitacaoResponseDTO
) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento){
        return new PagamentoResponseDTO(
                pagamento.getValor(),
                pagamento.getConfirmacaoPagamento(),
                pagamento.getDataConfirmacaoPagamento(),
                pagamento.getTipoPagamento(),
                SolicitacaoResponseDTO.valueOf(pagamento.getSolicitacao())
        );
    }
}
