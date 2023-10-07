package test;

import dto.CadastroDTO;
import dto.LoginDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.Test;
import service.CadastroService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

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
}
