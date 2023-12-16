package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.Hash.HashService;
import br.unitins.topicos1.service.Jwt.JwtService;
import br.unitins.topicos1.service.Usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    @POST
    public Response login(@Valid LoginDTO dto) {
        LOG.infof("Iniciando a autenticacao do %s", dto.login());

        String hashSenha = hashService.getHashSenha(dto.senha());

        LOG.infof(dto.senha());
        LOG.infof(hashSenha);
        LOG.info("Hash da senha gerado.");
        LOG.debug(hashSenha);

        UsuarioResponseDTO result = service.findByLoginAndSenha(dto.login(), hashSenha.toString());

        if (result != null)
            LOG.info("Login e senha corretos.");
        else
            LOG.info("Login e senha incorretos.");
        
        String token = jwtService.generateJwt(result);
        LOG.info("Finalizando o processo de login.");

        return Response.ok().header("Authorization", token).build();
    }
  
}
