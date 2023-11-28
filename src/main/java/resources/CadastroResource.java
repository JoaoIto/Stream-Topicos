package resources;
import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Cadastro;
import service.CadastroService;

    @Path("/cadastro")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public class CadastroResource {
        @Inject
        CadastroService service;

    @POST
    @Transactional
    @RolesAllowed({"admin", "user"})
    public Response insert(@Valid CadastroDTO dto){
        CadastroResponseDTO retorno = service.insert(dto);
        //return Response.status(Response.Status.fromStatusCode(200)).entity(retorno).build();
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
    @Path("/search/nickname/{nickname}")
    public Response findByNick(@PathParam("nickname") String nickname){
        return Response.ok(service.findByNick(nickname)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "user"})
    @Transactional
    public CadastroResponseDTO update(CadastroDTO dto, @PathParam("id") Long id, Cadastro cadastroAtualizado) {
        return service.update(dto, id);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

}
