package resources;

import dto.PedidoDTO;
import dto.PedidoResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import service.PedidoService;
    @Path("/pedido")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public class PedidoResource {
        @Inject
        PedidoService service;

        @Inject
        JsonWebToken jwt;

        @POST
        @Transactional
        @RolesAllowed({"admin", "user"})
        public Response insert(@Valid PedidoDTO dto) {
            String login = jwt.getSubject();
            PedidoResponseDTO retorno = service.insert(dto, login);
            //return Response.status(Response.Status.fromStatusCode(200)).entity(retorno).build();
            return Response.status(Response.Status.CREATED).entity(retorno).build();
        }

        @GET
        @RolesAllowed({"admin"})
        public Response findAll() {
            return Response.ok(service.findAll()).build();
        }
}
