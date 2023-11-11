package service;

import dto.CadastroResponseDTO;
import dto.LoginResponseDTO;

public interface JwtService {

    public String generateJwt(LoginResponseDTO dto);

}