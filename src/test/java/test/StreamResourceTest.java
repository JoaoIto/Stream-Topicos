package test;

import dto.StreamDTO;
import dto.StreamResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import service.StreamService;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void testInsert() {
        // Crie um objeto StreamDTO para inserção de teste
        StreamDTO streamDTO = new StreamDTO("Stream Teste", "Usuario Teste", 10.0f);

        // Insira o objeto de teste
        Response response = given()
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when()
                .post("/streams");

        // Verifique se a inserção foi bem-sucedida (código de status 201 para criação)
        response.then()
                .statusCode(201);

        // Extraia o StreamResponseDTO da resposta
        StreamResponseDTO streamResponseDTO = response.as(StreamResponseDTO.class);

        // Verifique se os campos foram corretamente inseridos
        assertEquals("Stream Teste", streamResponseDTO.nome());
        assertEquals("Usuario Teste", streamResponseDTO.nomeUsuario());
        assertEquals(10.0f, streamResponseDTO.custoStream(), 0.01); // Use um valor de tolerância para comparação de números decimais
    }

    @Test
    public void testUpdateStream() {
        // Crie um objeto StreamDTO para inserção de teste
        StreamDTO streamDTO = new StreamDTO("Stream Teste", "Usuario Teste", 10.0f);

        // Insira o objeto de teste
        StreamResponseDTO responseDTO = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when()
                .post("/streams")
                .then()
                .statusCode(201)
                .extract()
                .as(StreamResponseDTO.class);

        // Agora, atualize os campos e faça a chamada de atualização
        StreamDTO updatedDTO = new StreamDTO("Novo Nome", "Usuario Teste", 15.0f);

        // Atualize o Stream
        Response updateResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updatedDTO)
                .when()
                .put("/streams/{id}", responseDTO.id()) // Substitua {id} pelo ID correto
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Verifique se a atualização foi bem-sucedida
        StreamResponseDTO updatedResponse = updateResponse.as(StreamResponseDTO.class);
        assertEquals("Novo Nome", updatedResponse.nome());
        assertEquals("Usuario Teste", updatedResponse.nomeUsuario()); // Preservado a partir da inserção original
        assertEquals(15.0f, updatedResponse.custoStream(), 0.01); // Use um valor de tolerância para comparação de números decimais
    }

    @Test
    @Transactional
    public void testDeleteStream() {
        // Crie um objeto StreamDTO para inserção de teste
        StreamDTO streamDTO = new StreamDTO("Stream Teste", "Usuario Teste", 10.0f);

        // Insira o objeto de teste
        StreamResponseDTO responseDTO = given()
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when()
                .post("/streams")
                .then()
                .statusCode(201)
                .extract()
                .as(StreamResponseDTO.class);

        // Obtenha o ID do Stream inserido
        Long streamId = responseDTO.id();

        // Faça a chamada para deletar o Stream
        given()
                .when()
                .delete("/streams/{id}", streamId)
                .then()
                .statusCode(204);
    }

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
