package br.unitins.topicos1.dto.Pagamento;

import br.unitins.topicos1.dto.Solicitacao.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Pagamento.TipoPagamento;

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
