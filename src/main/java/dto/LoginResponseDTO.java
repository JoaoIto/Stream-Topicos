package dto;

import models.Cadastro;
import models.Login;
import models.Perfil;

public record LoginResponseDTO(
        Long id,
        String senha,
        Perfil perfil
) {
    public static LoginResponseDTO valueOf(Login login){
        return new LoginResponseDTO(
                login.getId(),
                login.getSenha(),
                login.getPerfil()
        );
    }
}
