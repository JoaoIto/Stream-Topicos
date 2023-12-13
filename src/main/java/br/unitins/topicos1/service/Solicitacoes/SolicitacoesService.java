package br.unitins.topicos1.service.Solicitacoes;

import br.unitins.topicos1.dto.Solicitacao.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Usuario.Usuario;
import jakarta.ws.rs.PathParam;

import java.util.List;

public interface SolicitacoesService {

    public SolicitacaoResponseDTO criarSolicitacao(Duo duo, Usuario usuario);

    public List<SolicitacaoResponseDTO> findAll();

    public SolicitacaoResponseDTO findById(@PathParam("id") Long id);

    /*
    
    void efetuarPagamentoBoleto(Long idUsuario);

    void efetuarPagamentoPix(Long idUsuario);

    void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO);

     */

}
