package br.unitins.topicos1.dto.Solicitacao;

import br.unitins.topicos1.dto.Duo.DuoResponseDTO;
import br.unitins.topicos1.dto.Pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.Pagamento.PagamentoToSolicitacaoResponseDTO;
import br.unitins.topicos1.model.Solicitacao.Solicitacao;
import br.unitins.topicos1.model.Pagamento.Pagamento;
import br.unitins.topicos1.model.Solicitacao.StatusSolicitacao;

public record SolicitacaoResponseDTO(
        Long id,
        DuoResponseDTO duo,
        Float valorTotal,
        StatusSolicitacao status,
        PagamentoToSolicitacaoResponseDTO pagamento
) {
    public static SolicitacaoResponseDTO valueOf(Solicitacao solicitacao) {
        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                DuoResponseDTO.valueOf(solicitacao.getDuo()),
                solicitacao.getDuo().calcularCustoTotal(),
                solicitacao.getStatus(),
                solicitacao.getPagamento() != null ? PagamentoToSolicitacaoResponseDTO.valueOf(solicitacao) : null
        );
    }
}
