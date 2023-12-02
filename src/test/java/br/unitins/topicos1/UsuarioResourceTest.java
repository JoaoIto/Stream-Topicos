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
//import javax.ws.rs.core.Response.Status;



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
            .statusCode(Status.CREATED.getStatusCode());
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
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .delete("/usuarios/1")
            .then()
            .statusCode(Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void testFindAll() {
        given()
            .when()
            .get("/usuarios")
            .then()
            .statusCode(Status.OK.getStatusCode());
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .get("/usuarios/1")
            .then()
            .statusCode(Status.OK.getStatusCode());
    }

    @Test
    public void testFindByNome() {
        // Assumindo que o nome "John" existe no banco de dados
        given()
            .when()
            .get("/usuarios/search/nome/John")
            .then()
            .statusCode(Status.OK.getStatusCode());
    }
}

    // @Test
    // public void testInsert() {
    //     List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
    //     telefones.add(new TelefoneDTO("63", "5555-5555"));

    //     UsuarioDTO dto = new UsuarioDTO(
    //         "Mark Zuckerberg Insert",
    //         "marquinho",
    //         "333",
    //         telefones
    //     );

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(dto)
    //         .when().post("/usuarios")
    //         .then()
    //         .statusCode(201)
    //         .body(
    //             "id", notNullValue(),
    //             "nome", is("Mark Zuckerberg Insert"),
    //             "login", is("marquinho")
    //         );
    // }

    // @Test
    // public void testUpdate() {
    //     List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
    //     telefones.add(new TelefoneDTO("63", "5555-5555"));

    //     UsuarioDTO dto = new UsuarioDTO(
    //         "Mark Zuckerberg Update",
    //         "marquinho",
    //         "333",
    //         telefones
    //     );

    //     // inserindo um usuario
    //     UsuarioResponseDTO usuarioTest = usuarioService.insert(dto);
    //     Long id = usuarioTest.id();

    //     UsuarioDTO dtoUpdate = new UsuarioDTO(
    //         "Mark Zuckerberg",
    //         "mark",
    //         "555",
    //         telefones
    //     );

    //     given()
    //         .contentType(ContentType.JSON)
    //         .body(dtoUpdate)
    //         .when().put("/usuarios/"+ id)
    //         .then()
    //         .statusCode(204);

    //     // verificando a alteracao

    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     System.out.println(id);
    //     UsuarioResponseDTO usu = usuarioService.findById(4l);
    //     assertThat(usu.nome(), is("Mark Zuckerberg"));
    //     assertThat(usu.login(), is("mark"));

    // }
