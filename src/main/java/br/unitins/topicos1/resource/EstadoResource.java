package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.Estado;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
public class EstadoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> todosEstados() {
        return Estado.listAll();
    }
    
}
