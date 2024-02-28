package br.unitins.topicos1.services.Pagamento;

import br.unitins.topicos1.dto.Pagamento.CartaoCredito.CartaoCreditoDTO;
import br.unitins.topicos1.dto.Pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.dto.Pagamento.Pix.PixDTO;

public interface PagamentoService {
    public PagamentoResponseDTO pagarPix(PixDTO dto);
    public PagamentoResponseDTO pagarCartaoCredito(CartaoCreditoDTO dto);
}
