package resources;

import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LoginService;

@Path("/loginlogado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginLog {

    @Inject
    JsonWebToken jwt;

    @Inject
    LoginService service;

    @GET
    @RolesAllowed({"Admin"})
    public Response getLogin() {
        // obtendo o login pelo token jwt
        String login = jwt.getSubject();

        return Response.ok(service.findByLoginAndSenha(login)).build();
    }

}