package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.*;
import br.unitins.topicos1.service.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResouce {
    @Inject
    PagamentoService service;

    private static final Logger LOG = Logger.getLogger(PagamentoResouce.class);

    @Path("/pix")
    @RolesAllowed({"User"})
    @POST
    @Transactional
    public Response postPixPagamento(@Valid PixDTO dto) {
        LOG.info("Fazendo pagamento via pix");
        PagamentoResponseDTO responseDTO = service.pagarPix(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @Path("/cartao")
    @RolesAllowed({"User"})
    @POST
    @Transactional
    public Response postCartaoPagamento(@Valid CartaoCreditoDTO dto) {
        LOG.info("Fazendo pagamento via cartao");
        LOG.info("A partir da solicitacao de id: " + dto.idSolicitacao());
        PagamentoResponseDTO responseDTO = service.pagarCartaoCredito(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }
}
