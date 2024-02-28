package br.unitins.topicos1.resource;

import java.util.logging.Logger;

import br.unitins.topicos1.dto.Usuario.alterarSenhaUsuarioDTO;
import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.services.Usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(String.valueOf(UsuarioLogadoResource.class));
    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuario() {

        // obtendo o login pelo token jwt
        String login = jwt.getSubject();
        Log.info("Pegando o usuário logado string: " + login);
        Log.info("Pegando o usuário logado");
        return Response.ok(usuarioService.findByLogin(login)).build();

    }

    @Path("/usuariologado")
    @PUT
    @RolesAllowed({"User", "Admin"})
    public Response putInfos(alterarSenhaUsuarioDTO senhaUsuarioDTO){
        String login = jwt.getSubject();
        Log.info("Pegando o usuário logado string: " + login);
        Log.info("Alterando a senha do usuário logado");
        usuarioService.alterarSenha(senhaUsuarioDTO, login);
        return Response.noContent().build();
    }



}
