package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import br.unitins.topicos1.model.Pagamento.Pagamento;

public record SolicitacaoResponseDTO(
        Long id,
        DuoResponseDTO duo,
        Float valorTotal,
        StatusSolicitacao status,
        Pagamento pagamento
) {
    public static SolicitacaoResponseDTO valueOf(Solicitacao solicitacao) {
        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                DuoResponseDTO.valueOf(solicitacao.getDuo()),
                solicitacao.getDuo().calcularCustoTotal(),
                solicitacao.getStatus(),
                solicitacao.getPagamento()
        );
    }
}
