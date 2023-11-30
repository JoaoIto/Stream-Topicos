package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

       if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());

        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));

        novoUsuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        if (dto.listaTelefone() != null && 
                    !dto.listaTelefone().isEmpty()){
            novoUsuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        }

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        usuario.setNome(dto.nome());
        usuario.setSenha(dto.senha());

        // falta a implementacao dos telefones
        // vcs (ALUNOS) devem implementar!!!!!
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Usuario usuario = repository.findById(id);
        usuario.setNomeImagem(nomeImagem);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
             return null;
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        try {
            Usuario usuario = repository.findByLoginAndSenha(login, senha);
            if (usuario == null) {
                throw new ValidationException("login", "Login ou senha inválido");
            }
            return UsuarioResponseDTO.valueOf(usuario);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir a pilha de exceção no console
            throw new ValidationException("login", "Ocorreu um erro durante a autenticação. Consulte os logs para obter mais informações.");
        }
    }


    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null) 
            throw new ValidationException("login", "Login inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }
    
}
