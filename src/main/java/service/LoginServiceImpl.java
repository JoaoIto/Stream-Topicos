package service;

import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import models.Login;
import repository.LoginRepository;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {

    @Inject
    LoginRepository repository;

    @Override
    public LoginResponseDTO insert(LoginDTO dto){
        Login novoLogin = new Login();
        novoLogin.setSenha(dto.senha());

        repository.persist(novoLogin);
        return LoginResponseDTO.valueOf(novoLogin);
    }

    @Override
    public LoginResponseDTO update(LoginDTO dto, Long id) {
        Login login = repository.findById(id);
        if(login == null){
            throw new NotFoundException("Login não encontrado!");
        }

        login.setSenha(dto.senha());

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
        return repository.listAll().stream().map(LoginResponseDTO::valueOf).collect(Collectors.toList());
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
    public LoginResponseDTO findByLoginCadastro(LoginDTO dto) {
        // Implemente a lógica correta para buscar os dados com base no DTO.
        // Por exemplo, você pode usar o repositório para buscar dados relacionados ao login.
        // O código abaixo é apenas um exemplo fictício:
        List<Login> login = repository.findByCadastro(dto.senha());
        if (login == null)
            throw new ValidationException("login");

        return LoginResponseDTO.valueOf((Login) login);
    }

    @Override
    public LoginResponseDTO findByLoginAndSenha(String login, String senha) {
        Login loginEntity = repository.findByLoginAndSenha(login, senha); // Implemente esse método no seu repositório
        if (loginEntity == null) {
            throw new NotFoundException("Login não encontrado!");
        }
        return LoginResponseDTO.valueOf(loginEntity);
    }
}