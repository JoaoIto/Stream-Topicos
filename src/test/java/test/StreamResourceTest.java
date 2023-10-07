package test;

import dto.StreamDTO;
import dto.StreamResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import service.StreamService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class StreamResourceTest {

    @Inject
    StreamService streamService;

    @Test
    public void testFindByNome() {
        // Insira um stream de teste antes de pesquisar por nome
        StreamDTO streamDTO = new StreamDTO("Stream Teste", "Usuario Teste", 10.0f);
        given()
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when().post("/streams")
                .then()
                .statusCode(201);

        // Pesquise por nome
        String nome = "Stream Teste"; // Defina o nome com base no valor usado na inserção

        given()
                .when()
                .get("streams/search/nome/" + nome)
                .then()
                .statusCode(200)
                .body("nome", hasItem(nome)); // Verifique se o nome está presente na resposta
    }

    @Test
    public void testFindByCusto() {
        // Insira um stream de teste antes de pesquisar por custo
        StreamDTO streamDTO = new StreamDTO("Stream Custo", "Usuario Custo", 20.0f);
        given()
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when().post("/streams")
                .then()
                .statusCode(201);

        // Pesquise por custo
        Float custo = 20.0f; // Defina o custo com base no valor usado na inserção

        given()
                .when()
                .get("streams/search/custoStream/" + custo)
                .then()
                .statusCode(200)
                .body("custoStream", hasItem(custo)); // Verifique se o custo está presente na resposta
    }
}
