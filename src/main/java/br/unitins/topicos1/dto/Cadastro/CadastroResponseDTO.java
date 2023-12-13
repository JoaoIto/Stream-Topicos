package br.unitins.topicos1.dto.Cadastro;

import br.unitins.topicos1.dto.Usuario.TelefoneDTO;
import br.unitins.topicos1.model.Usuario.Usuario;

import java.util.List;
import java.util.Optional;

public record CadastroResponseDTO(

        Long id,

        String nome,
        String login,
        String senha,
        String cpf,

        List<TelefoneDTO> listaTelefone
) {
    public static CadastroResponseDTO valueOf(Usuario usuario){

        return new CadastroResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getCpf(),
                Optional.ofNullable(usuario.getListaTelefone())
                        .map(telefones -> telefones.stream().map(TelefoneDTO::valueOf).toList())
                        .orElse(null)
        );
    }
}
