package br.unitins.topicos1.services.Cadastro;

import br.unitins.topicos1.dto.Cadastro.CadastroDTO;
import br.unitins.topicos1.dto.Cadastro.CadastroResponseDTO;
import br.unitins.topicos1.model.Usuario.Perfil;
import br.unitins.topicos1.model.Usuario.Usuario;
import br.unitins.topicos1.repositories.UsuarioRepository;
import br.unitins.topicos1.services.Hash.HashService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastroServiceImpl implements CadastroService {
    @Inject
    UsuarioRepository repository;
    @Inject
    HashService hashService;

    @Override
    @Transactional
    public CadastroResponseDTO insert(CadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setCpf(dto.cpf());
        usuario.setPerfil(Perfil.USER);

        repository.persist(usuario);

        return CadastroResponseDTO.valueOf(usuario);
    }

}
