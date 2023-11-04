package service;

import dto.LoginResponseDTO;

public interface JwtService {

    public String generateJwt(LoginResponseDTO dto);

}