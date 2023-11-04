package test;

import dto.CadastroResponseDTO;
import dto.LoginDTO;
import dto.LoginResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import models.Cadastro;
import models.Login;
import org.junit.jupiter.api.Test;
import service.CadastroService;
import service.LoginService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.util.function.Predicate.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class LoginResourceTest {

    @Inject
    LoginService loginService;

    @Inject
    CadastroService cadastroService;

    @Test
    public void testInsert() {
        LoginDTO loginDTO = new LoginDTO("123teste", "123");
        given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("/login")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "senha", is("123")
                );
    }

    @Test
    public void testFindAll() {
        LoginDTO loginDTO = new LoginDTO("123teste", "123");
        given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when().post("/login")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() {
        LoginDTO loginDTO = new LoginDTO("123teste", "123");
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when().post("/login");

        insertResponse.then()
                .statusCode(201);
        Long id = insertResponse.jsonPath().getLong("id");

        given()
                .when()
                .get("/login/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.intValue()));
    }

    @Test
    public void testFindByNick() {
        String nickname = "Nickname Teste"; // Defina o nickname com base no valor usado na inserção

        // Pesquise por nickname e obtenha a lista de Cadastro correspondente
        List<CadastroResponseDTO> cadastros = cadastroService.findByNick(nickname);

        // Acesse o Login associado a cada Cadastro
        for (CadastroResponseDTO cadastro : cadastros) {
            assertThat(cadastro.nickname(), is(nickname));
        }
    }

    @Test
    public void testUpdate() {
        LoginDTO loginDTO = new LoginDTO("123teste", "123");
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("/login");

        insertResponse.then()
                .statusCode(201);
        Long id = insertResponse.jsonPath().getLong("id");

        LoginDTO updatedDTO = new LoginDTO("SenhaAtualizada", "senha");

        Response updateResponse = given()
                .contentType(ContentType.JSON)
                .body(updatedDTO)
                .when()
                .put("/login/" + id);

        updateResponse.then()
                .statusCode(200);

        LoginResponseDTO updatedResponseDTO = updateResponse.as(LoginResponseDTO.class);
        assertEquals("senha", updatedResponseDTO.senha());
    }

    @Test
    public void testDelete() {
        LoginDTO loginDTO = new LoginDTO("123teste", "123");
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when().post("/login");

        insertResponse.then()
                .statusCode(201);
        Long id = insertResponse.jsonPath().getLong("id");

        given()
                .when()
                .delete("/login/" + id)
                .then()
                .statusCode(204);
    }
}
