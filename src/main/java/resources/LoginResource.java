package resources;
import dto.LoginDTO;
import dto.LoginResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Login;
import service.LoginService;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LoginResource {
    @Inject
    LoginService service;

    @POST
    @Transactional
    @RolesAllowed({"admin", "user"})
    public Response insert(@Valid LoginDTO dto){
        LoginResponseDTO retorno = service.insert(dto);
        return Response.status(Response.Status.CREATED).entity(retorno).build();
    }


    @GET
    @RolesAllowed({"admin"})
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({"admin", "user", "streamer"})
    @Path("/search/nickname/{nickname}")
    public Response findByNick(@PathParam("nickname") String nickname){
        return Response.ok(service.findByNick(nickname)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    @Transactional
    public LoginResponseDTO update(LoginDTO dto, @PathParam("id") Long id, Login loginAtualizado) {
        return service.update(dto, id);
    }

    @DELETE
    @RolesAllowed({"admin"})
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}
