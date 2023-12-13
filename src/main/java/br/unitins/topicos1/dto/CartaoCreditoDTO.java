package br.unitins.topicos1.dto;

import java.time.LocalDate;

public record CartaoCreditoDTO(
    Long idSolicitacao,
    String numeroCartao,
    String nomeImpressoCartao,
    LocalDate dataValidade,
    String codigoSeguranca,
    Integer bandeiraCartao
)
{

}
