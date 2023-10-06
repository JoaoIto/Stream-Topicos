package dto;

import models.Cadastro;
import models.Login;

public record CadastroResponseDTO(
        Long id,
        String nome,
        String email,
        String nickname,

        Login login
) {
    public static CadastroResponseDTO valueOf(Cadastro cadastro){
        return new CadastroResponseDTO(
                cadastro.getId(),
                cadastro.getNome(),
                cadastro.getEmail(),
                cadastro.getNickname(),
                cadastro.getLogin()
        );
    }

}
