package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoRepository repository;

    @POST
    @Transactional
    public Estado insert(Estado estado) {
        Estado novoEstado = new Estado();
        novoEstado.setNome(estado.getNome());
        novoEstado.setSigla(estado.getSigla());

        repository.persist(novoEstado);

        return novoEstado;
    }

    @GET
    public List<Estado> findAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Estado findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public List<Estado> findByNome(@PathParam("nome") String nome) {
        return repository.findByNome(nome);
    }
}
