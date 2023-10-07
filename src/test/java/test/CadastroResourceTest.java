package test;

import dto.CadastroDTO;
import dto.CadastroResponseDTO;
import dto.LoginDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import jakarta.inject.Inject;
import org.junit.Test;
import service.CadastroService;

import java.util.List;

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
        LoginDTO loginDTO = new LoginDTO("123teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);
        given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "nome", is("Nome Teste"),
                        "email", is("Email Teste"),
                        "nickname", is("Nickname Teste")
                );
    }

    @Test
    public void testFindAll() {
        // Crie um objeto CadastroDTO que inclua informações de Login
        LoginDTO loginDTO = new LoginDTO("123teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);

        // Insira os dados de teste
        given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(201);

        // Agora você pode testar o método findAll
        given()
                .when().get("/cadastro")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() {
        // Crie um objeto CadastroDTO que inclua informações de Login
        LoginDTO loginDTO = new LoginDTO("123teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);

        // Insira os dados de teste e obtenha o ID
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro");

        // Verifique se a inserção foi bem-sucedida e obtenha o ID
        insertResponse.then()
                .statusCode(201);
        Long id = insertResponse.jsonPath().getLong("id");

        // Testando o método findById
        given()
                .when()
                .get("/cadastro/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id.intValue())); // Compare como um inteiro
    }

    @Test
    public void testFindByNick() {
        // Crie um objeto CadastroDTO que inclua informações de Login
        LoginDTO loginDTO = new LoginDTO("123teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);

        // Insira os dados de teste
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when().post("/cadastro");

        // Verifique se a inserção foi bem-sucedida
        insertResponse.then()
                .statusCode(201);

        // Obtenha o nickname do cadastro inserido
        String nickname = "Nickname Teste"; // Defina o nickname com base no valor usado na inserção

        // Teste o método findByNick com o nickname inserido
        given()
                .when()
                .get("/cadastro/search/nickname/" + nickname)
                .then()
                .statusCode(200)
                .body("nickname", hasItem(nickname)); // Verifique se o nickname está presente na resposta
    }

    @Test
    public void testUpdate() {
        // Insira um cadastro de teste antes de atualizar
        LoginDTO loginDTO = new LoginDTO("Senha Teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);

        // Insira o cadastro e obtenha o ID
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when()
                .post("/cadastro");

        // Verifique se a inserção foi bem-sucedida
        insertResponse.then()
                .statusCode(201);

        Long id = insertResponse.jsonPath().getLong("id");

        // Crie um objeto de atualização
        CadastroDTO updatedDTO = new CadastroDTO("Nome Atualizado", "Email Atualizado", "Nickname Atualizado", new LoginDTO("Senha Atualizada"));

        // Atualize o cadastro com base no ID
        Response updateResponse = given()
                .contentType(ContentType.JSON)
                .body(updatedDTO)
                .when()
                .put("/cadastro/" + id);

        // Verifique se a atualização foi bem-sucedida
        updateResponse.then()
                .statusCode(200);

        // Obtenha o objeto CadastroResponseDTO da resposta
        CadastroResponseDTO updatedResponseDTO = updateResponse.as(CadastroResponseDTO.class);

        // Verifique se os campos foram atualizados corretamente
        assertEquals("Nome Atualizado", updatedResponseDTO.nome());
        assertEquals("Email Atualizado", updatedResponseDTO.email());
        assertEquals("Nickname Atualizado", updatedResponseDTO.nickname());
    }


    @Test
    public void testDelete() {
        // Insira um cadastro de teste antes de deletar
        LoginDTO loginDTO = new LoginDTO("123teste");
        CadastroDTO cadastroDTO = new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", loginDTO);

        // Insira o cadastro e obtenha o ID
        Response insertResponse = given()
                .contentType(ContentType.JSON)
                .body(cadastroDTO)
                .when()
                .post("/cadastro");

        // Verifique se a inserção foi bem-sucedida
        insertResponse.then()
                .statusCode(201);

        Long id = insertResponse.jsonPath().getLong("id");

        // Deleta o cadastro com base no ID
        given()
                .when()
                .delete("/cadastro/" + id)
                .then()
                .statusCode(204);
    }
}
