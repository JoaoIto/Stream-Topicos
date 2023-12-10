package br.unitins.topicos1.resource;

import jakarta.annotation.security.RolesAllowed;
import org.eclipse.microprofile.jwt.JsonWebToken;
import br.unitins.topicos1.service.SolicitacoesService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/solicitacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacoesResource {

    @Inject
    SolicitacoesService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;

    @RolesAllowed({"streamer", "Admin"})
    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }
}
