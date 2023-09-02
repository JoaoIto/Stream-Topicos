package dto;

import models.cadastro.Cadastro;

public record CadastroResponseDTO(
        Long id,
        String nome,
        String email,
        String nickname
) {
    public static CadastroResponseDTO valueOf(Cadastro cadastro){
        return new CadastroResponseDTO(
                cadastro.getId(),
                cadastro.getNome(),
                cadastro.getEmail(),
                cadastro.getNickname()
        );
    }

}
