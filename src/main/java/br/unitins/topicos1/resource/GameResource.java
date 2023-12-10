package br.unitins.topicos1.resource;


import br.unitins.topicos1.dto.GameDTO;
import br.unitins.topicos1.dto.GameResponseDTO;
import br.unitins.topicos1.service.GameService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    GameService service;

    @RolesAllowed({"streamer", "Admin"})
    @POST
    @Transactional
    public Response insert(@Valid GameDTO dto) {
        GameResponseDTO responseDTO = service.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @RolesAllowed({"streamer", "Admin"})
    @PUT
    @Path("/{id}")
    @Transactional
    //public GameResponseDTO update(GameDTO dto, @PathParam("id") Long id, Game gameAtualizado) {
    //    return service.update(dto, id);
    //}
     public Response update(@PathParam("id") Long id, @Valid GameDTO dto) {
        GameResponseDTO responseDTO = service.update(dto, id);
        return Response.ok(responseDTO).build();
    }

    @RolesAllowed({"Admin"})
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
}