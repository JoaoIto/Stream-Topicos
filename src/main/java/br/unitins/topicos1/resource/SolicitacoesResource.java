package br.unitins.topicos1.resource;

import jakarta.annotation.security.RolesAllowed;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.service.SolicitacoesService;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.service.UsuarioServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/solicitacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacoesResource {

    @Inject
    SolicitacoesService service;

    @Inject
    UsuarioService usuarioService;

    @Inject
    UsuarioServiceImpl solicitacaoServiceImpl;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(SolicitacoesResource.class);

    @RolesAllowed({"streamer", "Admin"})
    @GET
    public Response findAll() {
        LOG.info("Fazendo busca de todos os duo.");
        return Response.ok(service.findAll()).build();
    }

    
     /*
    @PATCH
    @Path("/carrinho/pagar-boleto")
    @RolesAllowed({ "User" })
    public Response pagarBoleto() {

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            solicitacaoServiceImpl.efetuarPagamentoBoleto(usuario.getId());

            LOG.info("Pagamento com boleto efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build();
        } catch (

        NullPointerException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-pix")
    @RolesAllowed({ "User" })
    public Response pagarPix() {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            solicitacaoService.efetuarPagamentoPix(usuario.getId());

            LOG.info("Pagamento com pix efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao efetuar o pagamento com pix.", e);
            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PATCH
    @Path("/carrinho/pagar-cartao-credito")
    @RolesAllowed({ "User" })
    public Response pagarCartaoCredito(CartaoCreditoDTO cartaoCreditoDTO) {
        Result result = null;

        try {

            String login = tokenJwt.getSubject();

            Usuario usuario = usuarioService.getByLogin(login);

            solicitacaoService.efetuarPagamentoCartaoCredito(usuario.getId(), cartaoCreditoDTO);

            LOG.info("Pagamento com cartão de crédito efetuado com sucesso.");
            return Response.status(Status.ACCEPTED).build();
        } catch (NullPointerException e) {
            LOG.error("Erro ao efetuar o pagamento com cartão de crédito.", e);
            result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }


     */

}
