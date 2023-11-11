package test;

import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import dto.LoginDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.junit.Test;
import service.CadastroService;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CadastroResourceTest {

    @Inject
    CadastroService cadastroService;

    @Test
    public void testInsert() {
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "email@teste.com", "Nickname Teste", loginDTO);
        given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Nome Teste"),
                        "email", is("email@teste.com"),
                        "nickname", is("Nickname Teste")
                );
    }

    @Test
    public void testInvalidInsert() {
        // Tente inserir um cadastro com dados inválidos que não atendem às validações
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Inválido", "emailinvalido", "Nickname Teste", loginDTO);
        given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(400); // Deve retornar um status 400 devido a dados inválidos
    }
    @Test
    public void testFindAll() {
        // Insira alguns registros para testar o findAll
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste 1", "email1@teste.com", "Nickname Teste 1", loginDTO);

        given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(201);

        // Agora teste o método findAll
        given()
                .when().get("/cadastro")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() {
        // Insira um registro de teste para testar o findById
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "email@teste.com", "Nickname Teste", loginDTO);

        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro");

        insertResponse.then()
                .statusCode(201);
        Long id = insertResponse.jsonPath().getLong("id");

        // Agora teste o método findById
        given()
                .when()
                .get("/cadastro/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.intValue()));
    }

    @Test
    public void testFindByNick() {
        // Insira um registro de teste para testar o findByNick
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "email@teste.com", "Nickname Teste", loginDTO);

        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro");

        insertResponse.then()
                .statusCode(201);

        String nickname = "Nickname Teste"; // Defina o nickname com base no valor usado na inserção

        // Agora teste o método findByNick
        given()
                .when()
                .get("/cadastro/search/nickname/" + nickname)
                .then()
                .statusCode(200)
                .body("nickname", hasItem(nickname));
    }

    @Test
    public void testUpdate() {
        // Insira um registro de teste antes de atualizar
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "email@teste.com", "Nickname Teste", loginDTO);

        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when()
                .post("/cadastro");

        insertResponse.then()
                .statusCode(201);

        Long id = insertResponse.jsonPath().getLong("id");

        // Crie um objeto de atualização
        CadastroDTO updatedDTO = new CadastroDTO("Nome Atualizado", "email@teste.com", "Nickname Atualizado", new LoginDTO("Senha Atualizada", 3));

        // Agora teste o método de atualização
        given()
                .contentType(ContentType.JSON)
                .body(updatedDTO)
                .when()
                .put("/cadastro/" + id)
                .then()
                .statusCode(200);

        // Obtenha o objeto CadastroResponseDTO da resposta
        CadastroResponseDTO updatedResponseDTO = given()
                .when()
                .get("/cadastro/" + id)
                .as(CadastroResponseDTO.class);

        // Verifique se os campos foram atualizados corretamente
        assertEquals("Nome Atualizado", updatedResponseDTO.nome());
        assertEquals("email@teste.com", updatedResponseDTO.email());
        assertEquals("Nickname Atualizado", updatedResponseDTO.nickname());
    }

    @Test
    public void testDelete() {
        // Insira um registro de teste antes de deletar
        LoginDTO loginDTO = new LoginDTO("123", 1);
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "email@teste.com", "Nickname Teste", loginDTO);

        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when()
                .post("/cadastro");

        insertResponse.then()
                .statusCode(201);

        Long id = insertResponse.jsonPath().getLong("id");

        // Agora teste o método de exclusão
        given()
                .when()
                .delete("/cadastro/" + id)
                .then()
                .statusCode(204);
    }
}
