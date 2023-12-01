package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.dto.StreamResponseDTO;
import br.unitins.topicos1.service.StreamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/streams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreamResource {

    @Inject
    StreamService streamService;

    @POST
    public Response insert(StreamDTO dto) {
        StreamResponseDTO responseDTO = streamService.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, StreamDTO dto) {
        StreamResponseDTO responseDTO = streamService.update(dto, id);
        if (responseDTO != null) {
            return Response.ok(responseDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        streamService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        StreamResponseDTO responseDTO = streamService.findById(id);
        if (responseDTO != null) {
            return Response.ok(responseDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(streamService.findByNome(nome)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(streamService.findAll()).build();
    }
}
