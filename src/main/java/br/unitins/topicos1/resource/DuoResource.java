package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.DuoDTO;
import br.unitins.topicos1.dto.DuoResponseDTO;
import br.unitins.topicos1.service.DuoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/duos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DuoResource {
    @Inject
    DuoService duoService;

    @GET
    public Response findAll() {
        return Response.ok(duoService.findAll()).build();
    }

    @POST
    public Response insert(DuoDTO dto){
        DuoResponseDTO responseDTO = duoService.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, DuoDTO dto) {
        DuoResponseDTO responseDTO = duoService.update(dto, id);
        return Response.ok(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        duoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        DuoResponseDTO responseDTO = DuoResponseDTO.valueOf(duoService.findById(id));
        return Response.ok(responseDTO).build();
    }
}
