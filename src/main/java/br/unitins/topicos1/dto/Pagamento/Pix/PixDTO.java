package br.unitins.topicos1.dto.Pagamento.Pix;

import br.unitins.topicos1.model.Pagamento.TipoDeChavePix;

public record PixDTO(
        Long idSolicitacao,
        Integer tipoDeChavePix
) {
}
