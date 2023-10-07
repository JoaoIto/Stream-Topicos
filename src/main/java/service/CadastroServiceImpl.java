package service;

import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;
import models.Cadastro;
import models.Login;
import repositorys.CadastroRepository;

import java.util.List;

@ApplicationScoped
public class CadastroServiceImpl implements CadastroService{
    @Inject
    LoginService loginService;
    @Inject
    CadastroRepository repository;



    @Override
    public CadastroResponseDTO insert(CadastroDTO dto) {
        Cadastro novoCadastro = new Cadastro();

        novoCadastro.setNome(dto.getNome());
        novoCadastro.setEmail(dto.getEmail());
        novoCadastro.setNickname(dto.getNickname());

        // Use o LoginService para criar o Login
        LoginDTO loginDTO = dto.getLogin();
        LoginResponseDTO novoLogin = loginService.insert(loginDTO);
        Login login = loginService.findById(novoLogin.id());

        // Associe o Login ao Cadastro
        novoCadastro.setLogin(login);

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
    public CadastroResponseDTO update(CadastroDTO dto, Long id) {
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
