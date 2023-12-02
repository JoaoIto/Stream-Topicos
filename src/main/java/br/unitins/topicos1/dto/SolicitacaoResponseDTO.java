package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Solicitacao;

public record SolicitacaoResponseDTO(
        Long id,
        DuoResponseDTO duo,
        Float valorTotal
) {
    public static SolicitacaoResponseDTO valueOf(Solicitacao solicitacao) {
        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                DuoResponseDTO.valueOf(solicitacao.getDuo()),
                solicitacao.getDuo().calcularCustoTotal()
        );
    }
}
