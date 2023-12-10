package br.unitins.topicos1.resource;


import br.unitins.topicos1.dto.GameDTO;
import br.unitins.topicos1.dto.GameResponseDTO;
import br.unitins.topicos1.form.GameImageForm;
import br.unitins.topicos1.service.GameFileService;
import br.unitins.topicos1.service.GameService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    GameService gameService;
    private static final Logger LOG = Logger.getLogger(GameResource.class);

    @Inject
    GameFileService fileService;

    @Inject
    JsonWebToken jwt;



    @RolesAllowed({"streamer", "Admin"})
    @POST
    @Transactional
    public Response insert(@Valid GameDTO dto) {
        LOG.info("Cadastrando um game.");
        GameResponseDTO responseDTO = gameService.insert(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    public Response findAll(){
        LOG.info("Fazendo get de todos os games.");
        return Response.ok(gameService.findAll()).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.info("Fazendo get de game pelo id");
        return Response.ok(gameService.findById(id)).build();
    }

    @RolesAllowed({ "User", "streamer", "Admin"})
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Fazendo get de game pelo nome");
        return Response.ok(gameService.findByNome(nome)).build();
    }

    @RolesAllowed({"streamer", "Admin"})
    @PUT
    @Path("/{id}")
    @Transactional
    //public GameResponseDTO update(GameDTO dto, @PathParam("id") Long id, Game gameAtualizado) {
    //    return service.update(dto, id);
    //}
     public Response update(@PathParam("id") Long id, @Valid GameDTO dto) {
        LOG.info("Fazendo update de game pelo id");
        GameResponseDTO responseDTO = gameService.update(dto, id);
        return Response.ok(responseDTO).build();
    }

    @RolesAllowed({"Admin"})
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Fazendo delete de game pelo id");
        gameService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/upload/imagem/{id}")
    @RolesAllowed({ "User", "Admin", "streamer"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm GameImageForm form, @PathParam("id") Long id) throws IOException {
        String nomeImagem;
        nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        GameResponseDTO gameDTO = gameService.findById(id);
        gameDTO = gameService.updateNomeImagem(gameDTO.id(), nomeImagem);

        return Response.ok(gameDTO).build();

    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin", "streamer" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
    }
}