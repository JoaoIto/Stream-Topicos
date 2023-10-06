package service;

import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.ws.rs.PathParam;
import models.Login;
import java.util.List;

public interface LoginService {
    public LoginResponseDTO insert(LoginDTO dto);

    public LoginResponseDTO update(LoginDTO dto, Long id);

    public void delete(Long id);

    public List<LoginResponseDTO> findAll();

    public List<LoginResponseDTO> findByNick(@PathParam("nickname") String nickname);

    public Login findById(@PathParam("id") Long id);
}
