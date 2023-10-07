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
        // Insira um cadastro de teste antes de buscar pelo ID
        CadastroResponseDTO cadastroResponseDTO = cadastroService.insert(new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", new LoginDTO("Senha Teste")));
        Long id = cadastroResponseDTO.id();

        given()
                .when()
                .get("/cadastro/" + id)
                .then()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void testFindByNick() {
        // Insira um cadastro de teste antes de buscar pelo nickname
        CadastroResponseDTO cadastroResponseDTO = cadastroService.insert(new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", new LoginDTO("Senha Teste")));
        String nickname = "Nickname Teste";

        given()
                .when()
                .get("/cadastro/search/nickname/" + nickname)
                .then()
                .statusCode(200)
                .body("nickname[0]", equalTo(nickname));
    }

    @Test
    public void testUpdate() {
        // Insira um cadastro de teste antes de atualizar
        CadastroResponseDTO cadastroResponseDTO = cadastroService.insert(new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", new LoginDTO("Senha Teste")));
        Long id = cadastroResponseDTO.id();

        CadastroDTO updatedDTO = new CadastroDTO("Nome Atualizado", "Email Atualizado", "Nickname Atualizado", new LoginDTO("Senha Atualizada"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedDTO)
                .when()
                .put("/cadastro/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        CadastroResponseDTO updatedResponseDTO = response.as(CadastroResponseDTO.class);
        assertEquals("Nome Atualizado", updatedResponseDTO.nome());
        assertEquals("Email Atualizado", updatedResponseDTO.email());
        assertEquals("Nickname Atualizado", updatedResponseDTO.nickname());
    }

    @Test
    public void testDelete() {
        // Insira um cadastro de teste antes de deletar
        CadastroResponseDTO cadastroResponseDTO = cadastroService.insert(new CadastroDTO("Nome Teste", "Email Teste", "Nickname Teste", new LoginDTO("Senha Teste")));
        Long id = cadastroResponseDTO.id();

        given()
                .when()
                .delete("/cadastro/" + id)
                .then()
                .statusCode(204);
    }
}
