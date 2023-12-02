package br.unitins.topicos1;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult.Status;

import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.service.UsuarioService;


import javax.inject.Inject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;


    @Test
    public void testInsert() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("John Doe", "john.doe", "senha123", 1, null);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTO)
            .when()
            .post("/usuarios")
            .then()
            .statusCode(201);
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        UsuarioDTO usuarioDTO = new UsuarioDTO("John Updated", "john.updated", "updatedPassword", 1, null);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTO)
            .when()
            .put("/usuarios/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .delete("/usuarios/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testFindAll() {
        given()
            .when()
            .get("/usuarios")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .get("/usuarios/1")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindByNome() {
        // Assumindo que o nome "John" existe no banco de dados
        given()
            .when()
            .get("/usuarios/search/nome/John")
            .then()
            .statusCode(200);
    }
}
