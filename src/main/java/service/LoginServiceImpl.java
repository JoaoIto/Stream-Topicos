package service;

import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;
import models.Login;
import repositorys.LoginRepository;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {

    @Inject
    LoginRepository repository;

    @Override
    public LoginResponseDTO insert(LoginDTO dto){
        Login novoLogin = new Login();
        novoLogin.setSenha(dto.getSenha());

        repository.persist(novoLogin);
        return LoginResponseDTO.valueOf(novoLogin);
    }

    @Override
    public LoginResponseDTO update(LoginDTO dto, Long id) {
        Login login = repository.findById(id);
        if(login == null){
            throw new NotFoundException("Login não encontrado!");
        }

        login.setSenha(dto.getSenha());

        return LoginResponseDTO.valueOf(login);
    }

    @Override
    public void delete(Long id) {
        Login login = repository.findById(id);
        if(login == null){
            throw new NotFoundException("Login não encontrado!");
        }

        repository.delete(login);
    }

    @Override
    public List<LoginResponseDTO> findAll(){
        return repository.listAll().stream().map(LoginResponseDTO::valueOf).toList();
    }

    @Override
    public Login findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<LoginResponseDTO> findByNick(String nickname) {
        List<Login> logins = repository.find("login.cadastro.nickname", nickname).list();
        return logins.stream()
                .map(login -> new LoginResponseDTO(login.getId(), login.getSenha()))
                .collect(Collectors.toList());
    }

    @Override
    public LoginResponseDTO findByLoginAndSenha(LoginDTO dto) {
        LoginResponseDTO login = repository.findByLoginAndSenha(dto.getSenha(), senha);
        if (login == null)
            throw new ValidationException("login", "Login ou senha inválido");

        return LoginResponseDTO.valueOf(login);
    }
}
