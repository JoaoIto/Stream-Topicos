package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CadastroDTO;
import br.unitins.topicos1.dto.CadastroResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadastroServiceImpl implements CadastroService{
    @Inject
    UsuarioRepository repository;
    @Override
    @Transactional
    public CadastroResponseDTO insert(CadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setSenha(dto.senha());
        usuario.setCpf(dto.cpf());
        usuario.setPerfil(Perfil.USER);

        repository.persist(usuario);

        return CadastroResponseDTO.valueOf(usuario);
    }

}
