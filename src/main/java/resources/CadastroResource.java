package resources;
import dto.CadastroDto;
import dto.CadastroResponseDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.cadastro.Cadastro;
import repositorys.CadastroRepository;

import java.util.List;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroResource {
    @Inject
    CadastroRepository repository;

    @POST
    @Transactional
    public CadastroResponseDTO insert(CadastroDto dto){
        Cadastro novoCadastro = new Cadastro();
        novoCadastro.setNome(dto.getNome());
        novoCadastro.setEmail(dto.getEmail());
        novoCadastro.setNickname(dto.getNickname());

        repository.persist(novoCadastro);
        return CadastroResponseDTO.valueOf(novoCadastro);
    }

    @GET
    public List<CadastroResponseDTO> findAll(){
        return repository.listAll().stream().map(CadastroResponseDTO::valueOf).toList();
    }

    @GET
    @Path("/{id}")
    public Cadastro findById(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @GET
    @Path("/search/nickname/{nickname}")
    public List<Cadastro> findByNick(@PathParam("nickname") String nickname){
        return repository.findByNick(nickname);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cadastro update(@PathParam("id") Long id, Cadastro cadastroAtualizado) {
        Cadastro cadastro = repository.findById(id);
        if (cadastro == null) {
            throw new NotFoundException("Cadastro não encontrado com ID: " + id);
        }

        cadastro.setNome(cadastroAtualizado.getNome());
        cadastro.setEmail(cadastroAtualizado.getEmail());
        cadastro.setNickname(cadastroAtualizado.getNickname());

        return cadastro;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public List<Cadastro> delete(@PathParam("id") Long id) {
        Cadastro cadastro = repository.findById(id);
        if (cadastro == null) {
            throw new NotFoundException("Cadastro não encontrado com ID: " + id);
        }

        repository.delete(cadastro);
        return findAll();
    }

}
