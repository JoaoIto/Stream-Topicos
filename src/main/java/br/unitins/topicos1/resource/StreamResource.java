package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.dto.StreamResponseDTO;
import br.unitins.topicos1.service.StreamService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@Path("/streams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreamResource {

    @Inject
    StreamService streamService;

    private static final Logger LOG = Logger.getLogger(String.valueOf(StreamResource.class));

    @RolesAllowed({"streamer"})
    @POST
    public Response insert(StreamDTO dto) {
        LOG.info("Fazendo post de um stream.");
        StreamResponseDTO responseDTO = streamService.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }
    @RolesAllowed({"streamer", "Admin"})

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, StreamDTO dto) {
        LOG.info("Fazendo update de um stream pelo id.");
        StreamResponseDTO responseDTO = streamService.update(dto, id);
        if (responseDTO != null) {
            return Response.ok(responseDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @RolesAllowed({"streamer", "Admin"})
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Fazendo delete de um stream pelo id.");
        streamService.delete(id);
        return Response.noContent().build();
    }
    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Fazendo a busca de um stream pelo id.");
        StreamResponseDTO responseDTO = streamService.findById(id);
        if (responseDTO != null) {
            return Response.ok(responseDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @RolesAllowed({ "User", "streamer", "Admin"})

    @GET
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Fazendo a busca de um stream pelo nome.");
        return Response.ok(streamService.findByNome(nome)).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    public Response findAll() {
        LOG.info("Fazendo a busca de todos os stream.");
        return Response.ok(streamService.findAll()).build();
    }
}
