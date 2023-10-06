package service;

import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;
import models.Login;
import repositorys.LoginRepository;
import java.util.List;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {

    @Inject
    LoginRepository repository;

    @Override
    public LoginResponseDTO insert(LoginDTO dto){
        Login novoLogin = new Login();
        novoLogin.setNickname(dto.getNickname());
        novoLogin.setSenha(dto.getSenha());

        repository.persist(novoLogin);
        return  LoginResponseDTO.valueOf(novoLogin);
    }

    @Override
    public LoginResponseDTO update(LoginDTO dto, Long id) {
        Login login = repository.findById(id);
        if(login == null){
            throw new NotFoundException("Login não encontrado!");
        }

        login.setNickname(dto.getNickname());
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
    public List<LoginResponseDTO> findByNick(@PathParam("nickname") String nickname){
        return repository.findByNick(nickname).stream().map(LoginResponseDTO::valueOf).toList();
    }
}
