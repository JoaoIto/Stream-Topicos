package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public UsuarioResponseDTO update(UsuarioDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<UsuarioResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public List<UsuarioResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
}
