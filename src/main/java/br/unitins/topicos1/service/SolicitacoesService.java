package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.dto.SolicitacaoDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Solicitacao;
import jakarta.ws.rs.PathParam;

import java.util.List;

public interface SolicitacoesService {

    public SolicitacaoResponseDTO insert(DuoResponseDTO duo);
    public List<SolicitacaoResponseDTO> findAll();

    public Solicitacao findById(@PathParam("id") Long id);
}
