package dto;

import models.Login;

public record LoginResponseDTO(
        Long id,
        String nickname,
        String senha
) {
    public static LoginResponseDTO valueOf(Login login){
        return new LoginResponseDTO(
                login.getId(),
                login.getNickname(),
                login.getSenha()
        );
    }

}
