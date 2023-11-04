package resources;
import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.HashService;
import service.JwtService;
import service.LoginService;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    LoginService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(@Valid LoginDTO dto) {
        String hashSenha = hashService.getHashSenha(dto.senha());
        LoginResponseDTO result = service.findByLoginAndSenha(dto.login(), dto.senha());

        return Response.ok().entity(result).build();
    }


}