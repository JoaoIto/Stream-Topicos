package resources;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import application.Error;
import dto.CadastroResponseDTO;
import form.CadastroImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CadastroService;
import service.FileService;
import service.CadastroFileService;

import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import service.LoginService;

@Path("/loginlogado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginLogResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    LoginService service;

    @Inject
    CadastroFileService fileService;

    @GET
    @RolesAllowed({"admin", "user", "streamer"})
    public Response getLogin() {
        // obtendo o login pelo token jwt
        String login = jwt.getSubject();
        Long loginLong = Long.parseLong(login);
        return Response.ok(service.findById(loginLong)).build();
    }

    @PATCH
    @Path("/upload/imagem")
    @RolesAllowed({"admin", "user", "streamer"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm CadastroImageForm form){
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        String login = jwt.getSubject();
        CadastroResponseDTO cadastroDTO = CadastroService.findByLogin(login);
        cadastroDTO = cadastroService.updateNomeImagem(cadastroDTO.id(), nomeImagem);

        return Response.ok(cadastroDTO).build();

    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({"admin", "user", "streamer"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
    }

}