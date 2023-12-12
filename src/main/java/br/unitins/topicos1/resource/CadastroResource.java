package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CadastroDTO;
import br.unitins.topicos1.service.CadastroService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroResource {
    @Inject
    CadastroService service;

    private static final Logger LOG = Logger.getLogger(String.valueOf(CadastroResource.class));

    @POST
    public Response insert(CadastroDTO dto) {
        LOG.info("Cadastrando um usuario.");
        return Response.status(Response.Status.CREATED).entity(service.insert(dto)).build();
    }
}
