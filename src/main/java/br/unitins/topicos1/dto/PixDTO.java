package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento.TipoDeChavePix;

public record PixDTO(
        Long idSolicitacao,
        TipoDeChavePix tipoDeChavePix
) {
}
