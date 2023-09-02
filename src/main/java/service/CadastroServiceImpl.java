package service;

import dto.CadastroDto;
import dto.CadastroResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;
import models.cadastro.Cadastro;
import repositorys.CadastroRepository;

import java.util.List;

@ApplicationScoped
public class CadastroServiceImpl implements CadastroService{
    @Inject
    CadastroRepository repository;

    @Override
    public CadastroResponseDTO insert(CadastroDto dto) {
        Cadastro novoCadastro = new Cadastro();
        novoCadastro.setNome(dto.getNome());
        novoCadastro.setEmail(dto.getEmail());
        novoCadastro.setNickname(dto.getNickname());

        repository.persist(novoCadastro);
        return CadastroResponseDTO.valueOf(novoCadastro);
    }

    @Override
    public List<CadastroResponseDTO> findAll(){
        return repository.listAll().stream().map(CadastroResponseDTO::valueOf).toList();
    }

    public Cadastro findById(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @Override
    public List<CadastroResponseDTO> findByNick(@PathParam("nickname") String nickname){
        return repository.findByNick(nickname).stream().map(CadastroResponseDTO::valueOf).toList();
    }

    @Override
    public CadastroResponseDTO update(CadastroDto dto, Long id) {
        Cadastro cadastro = repository.findById(id);
        if (cadastro == null) {
            throw new NotFoundException("Cadastro não encontrado com ID: " + id);
        }

        cadastro.setNome(dto.getNome());
        cadastro.setEmail(dto.getEmail());
        cadastro.setNickname(dto.getNickname());

        return CadastroResponseDTO.valueOf(cadastro);
    }

    @Override
    public void delete(Long id) {
        Cadastro cadastro = repository.findById(id);
        if (cadastro == null) {
            throw new NotFoundException("Cadastro não encontrado com ID: " + id);
        }

        repository.delete(cadastro);
    }
}
