package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.Usuario;
import jakarta.ws.rs.PathParam;

import java.util.List;

public interface SolicitacoesService {

    public SolicitacaoResponseDTO criarSolicitacao(Duo duo, Usuario usuario);

    public List<SolicitacaoResponseDTO> findAll();

    public Solicitacao findById(@PathParam("id") Long id);

    /*
    
    void efetuarPagamentoBoleto(Long idUsuario);

    void efetuarPagamentoPix(Long idUsuario);

    void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO);

     */

}
