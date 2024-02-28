package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.services.Hash.HashService;
import br.unitins.topicos1.services.Usuario.UsuarioService;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.Game.GameDTO;
import br.unitins.topicos1.services.Game.GameService;
import br.unitins.topicos1.services.Jwt.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class GameResourceTest {
    
    @Inject
    GameService gameService;

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Test
    public void testInsert() {
        // Crie um jogo fictício
        GameDTO gameDTO = new GameDTO(
                "Jogo Teste",
                "Categoria Teste",
                null
        );

        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + tokenStreamer)
                        .contentType(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(gameDTO)
                .when().post("/games")
                .then()
                .statusCode(201);
    }


    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        GameDTO gameDTO = new GameDTO(
            "vavazinho Updated", 
            "fps Updated",
                null);

        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + tokenStreamer)
            .contentType(ContentType.JSON)
            .body(gameDTO)
            .when()
            .put("/games/2")
            .then()
            .statusCode(200);
    }

    @Test
    public void testDelete() {
        LoginDTO loginDTO = new LoginDTO("gates", "senha2");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenAdm = jwtService.generateJwt(result);
        // Assumindo que o ID 1 existe no banco de dados
        given()
                .headers("Authorization", "Bearer " + tokenAdm)
                .when()
            .delete("/games/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testFindAll() {
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        given()
                .headers("Authorization", "Bearer " + tokenStreamer)
                .when()
            .get("/games")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindById() {
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        // Assumindo que o ID 1 existe no banco de dados
        given()
                .headers("Authorization", "Bearer " + tokenStreamer)
                .when()
            .get("/games/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindByNome() {
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        // Assumindo que o nome "vavazinho" existe no banco de dados
        given()
                .headers("Authorization", "Bearer " + tokenStreamer)
            .when()
            .get("/games/search/nome/John")
            .then()
            .statusCode(200);
    }
}
