package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.dto.Duo.DuoResponseDTO;
import br.unitins.topicos1.service.Duo.DuoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Path("/duos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DuoResource {
    @Inject
    DuoService duoService;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(DuoResource.class);

    @RolesAllowed({ "streamer", "Admin"})
    @GET
    public Response findAll() {
        LOG.info("Fazendo get de todos os duo.");
        return Response.ok(duoService.findAll()).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @POST
    public Response insert(DuoDTO dto){
        LOG.info("Fazendo post de um duo.");
        String login = jwt.getSubject();
        DuoResponseDTO responseDTO = duoService.insert(dto, login);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @RolesAllowed({"streamer", "Admin"})
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, DuoDTO dto) {
        LOG.info("Fazendo update de um duo pelo id.");
        DuoResponseDTO responseDTO = duoService.update(dto, id);
        return Response.ok(responseDTO).build();
    }
    @RolesAllowed({"Admin"})

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Fazendo delete de um duo pelo id.");
        duoService.delete(id);
        return Response.noContent().build();
    }

    @RolesAllowed({"streamer", "Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Fazendo busca de um duo pelo id.");
        DuoResponseDTO responseDTO = DuoResponseDTO.valueOf(duoService.findById(id));
        return Response.ok(responseDTO).build();
    }
}
