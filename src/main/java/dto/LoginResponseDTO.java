package dto;

import models.Cadastro;
import models.Login;

public record LoginResponseDTO(
        Long id,
        String senha
) {
    public static LoginResponseDTO valueOf(Login login){
        return new LoginResponseDTO(
                login.getId(),
                login.getSenha()
        );
    }
}
