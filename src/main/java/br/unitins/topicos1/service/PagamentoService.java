package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PixDTO;

public interface PagamentoService {
    public PagamentoResponseDTO pagarPix(PixDTO dto);
    public PagamentoResponseDTO pagarCartaoCredito(CartaoCreditoDTO dto);
}
