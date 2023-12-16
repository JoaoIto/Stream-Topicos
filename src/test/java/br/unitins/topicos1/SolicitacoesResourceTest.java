package br.unitins.topicos1;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.repository.DuoRepository;
import br.unitins.topicos1.repository.GameRepository;
import br.unitins.topicos1.repository.StreamRepository;
import br.unitins.topicos1.service.Duo.DuoService;
import br.unitins.topicos1.service.Hash.HashService;
import br.unitins.topicos1.service.Jwt.JwtService;
import br.unitins.topicos1.service.Solicitacoes.SolicitacoesService;
import br.unitins.topicos1.service.Usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SolicitacoesResourceTest {
    @Inject
    JsonWebToken jwt;

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOG = Logger.getLogger(SolicitacoesResourceTest.class);

    @Test
    public void testInsertDuoAndGet() {
        // Crie um cadastro fictício para o teste
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(1L);
        listGames.add(2L);
        listGames.add(3L);
        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);
        LoginDTO loginDTO = new LoginDTO("musk", "senha1");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenUser = jwtService.generateJwt(result);
        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .body(duoDTO)
                .when().post("/duos")
                .then()
                .statusCode(201)
        ;

        // Aqui entendendo que o duo 1 foi criado, a solicitação 1 foi encontrada no banco de dados;

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .when().get("/solicitacoes/1")
                .then()
                .statusCode(200)
        ;
    }
}