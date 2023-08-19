package resources;
import jakarta.inject.Inject;
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

    @GET
    public List<Cadastro> findAll(){
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Cadastro findById(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<Cadastro> findByName(@PathParam("nome") String nome){
        return repository.findByNome(nome);
    }
}
