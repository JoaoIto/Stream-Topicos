package dto;

import models.Cadastro;
import models.Login;
import models.Perfil;

public record CadastroResponseDTO(
        Long id,
        String nome,
        String email,
        String nickname,

        Login login,
        String nomeImagem,

        Perfil perfil
) {
    public static CadastroResponseDTO valueOf(Cadastro cadastro){
        return new CadastroResponseDTO(
                cadastro.getId(),
                cadastro.getNome(),
                cadastro.getEmail(),
                cadastro.getNickname(),
                cadastro.getLogin(),
                cadastro.getNomeImagem(),
                cadastro.getLogin().getPerfil()
        );
    }

}
