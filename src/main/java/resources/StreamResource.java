package resources;

import dto.StreamDTO;
import dto.StreamResponseDTO;
import service.StreamService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/streams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StreamResource {

    @Inject
    StreamService service;

    @POST
    @Transactional
    public Response insert(@Valid StreamDTO dto) {
        StreamResponseDTO responseDTO = service.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }
}
