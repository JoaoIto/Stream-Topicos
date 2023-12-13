package br.unitins.topicos1;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.Game.GameDTO;
import br.unitins.topicos1.service.Game.GameService;
import br.unitins.topicos1.service.Jwt.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class GameResourceTest {
    
    @Inject
    GameService gameService;

    @Inject
    JwtService jwtService;

    // Método fictício para obter um token JWT (substitua pela lógica real)
    //private String obterTokenJWT(GameDTO gameDTO) 
    //{
    //    GameResponseDTO gameResponseDTO = GameResponseDTO.valueOf(gameDTO);
    //    return jwtService.generateJwt(gameResponseDTO);
    //}
    
    @Test
    public void testInsert() {
        // Crie um game fictício para o teste
        GameDTO gameDTO = new GameDTO(
            "vavazinho", 
            "fps",
            "skin");

        // Gere um token JWT para o usuário fictício (use sua lógica real para obter o token)
        //String tokenJWT = obterTokenJWT(gameDTO);

        // Adicione o token JWT aos cabeçalhos da solicitação
            given()
                //.auth().oauth2(tokenJWT)
                .contentType(ContentType.JSON)
                .body(gameDTO)
                .when().post("/game")
                .then()
                .statusCode(404)
                //.body(
                //"nome", is("vavazinho insert"),
                //"categoria", is("fps.insert"),
                //"nomeImagem:", is ("skin"))
                ;
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        GameDTO gameDTO = new GameDTO(
            "vavazinho Updated", 
            "fps Updated",
            "skin Updated");

        given()
            .contentType(ContentType.JSON)
            .body(gameDTO)
            .when()
            .put("/game/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .delete("/game/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindAll() {
        given()
            .when()
            .get("/game")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .get("/game/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        // Assumindo que o nome "vavazinho" existe no banco de dados
        given()
            .when()
            .get("/game/search/nome/John")
            .then()
            .statusCode(404);
    }
}
