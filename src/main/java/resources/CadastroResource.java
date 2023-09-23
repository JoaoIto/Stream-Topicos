package resources;
import dto.CadastroDto;
import dto.CadastroResponseDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.cadastro.Cadastro;
import service.CadastroService;

import java.util.List;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroResource {
    @Inject
    CadastroService service;

    @POST
    @Transactional
    public Response insert(@Valid CadastroDto dto){
        CadastroResponseDTO retorno = service.insert(dto);
        //return Response.status(Response.Status.fromStatusCode(200)).entity(retorno).build();
        return Response.status(Response.Status.CREATED).entity(retorno).build();
    }

    @GET
    public List<CadastroResponseDTO> findAll(){
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Cadastro findById(@PathParam("id") Long id){
        return service.findById(id);
    }

    @GET
    @Path("/search/nickname/{nickname}")
    public List<CadastroResponseDTO> findByNick(@PathParam("nickname") String nickname){
        return service.findByNick(nickname);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public CadastroResponseDTO update(CadastroDto dto, @PathParam("id") Long id, Cadastro cadastroAtualizado) {
        return service.update(dto, id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

}
