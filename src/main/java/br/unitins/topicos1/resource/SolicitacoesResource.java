package br.unitins.topicos1.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.services.Solicitacoes.SolicitacoesService;
import br.unitins.topicos1.services.Usuario.UsuarioService;
import br.unitins.topicos1.services.Usuario.UsuarioServiceImpl;
import jakarta.inject.Inject;
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

    @RolesAllowed({"User", "Admin"})
    @GET
    public Response findAll() {
        LOG.info("Fazendo busca de todos as solicitacoes.");
        return Response.ok(service.findAll()).build();
    }

    @RolesAllowed({"User", "Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Fazendo busca de uma solicitacao pelo id.");
        return Response.ok(service.findById(id)).build();
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
