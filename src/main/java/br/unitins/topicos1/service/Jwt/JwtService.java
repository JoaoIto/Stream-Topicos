package br.unitins.topicos1.service.Jwt;

import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;

public interface JwtService {

    public String generateJwt(UsuarioResponseDTO dto);
    
}
