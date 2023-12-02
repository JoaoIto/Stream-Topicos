package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.dto.SolicitacaoDTO;
import br.unitins.topicos1.dto.SolicitacaoResponseDTO;
import br.unitins.topicos1.model.Duo;
import br.unitins.topicos1.model.Solicitacao;
import br.unitins.topicos1.model.StatusSolicitacao;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.SolicitacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SolicitacoesServiceImpl implements SolicitacoesService {

    @Inject
    SolicitacaoRepository repository;

    @Inject
    DuoService duoService;


    @Override
    @Transactional
    public SolicitacaoResponseDTO insert(DuoResponseDTO duo) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setDuo(duo);
        solicitacao.setValorTotal(duo.calcularPrecoStream());
        solicitacao.setStatus(StatusSolicitacao.AGUARDANDO);

        repository.persist(solicitacao);
        return SolicitacaoResponseDTO.valueOf(solicitacao);
    }

    @Override
    public List<SolicitacaoResponseDTO> findAll() {
        List<Solicitacao> solicitacaos = repository.listAll();
        return solicitacaos.stream()
                .map(SolicitacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public Solicitacao findById(Long id) {
        return null;
    }
}
