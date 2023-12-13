package br.unitins.topicos1;
import br.unitins.topicos1.dto.StreamResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Stream;
import br.unitins.topicos1.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.StreamDTO;
import br.unitins.topicos1.service.StreamService;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class StreamResourceTest {
    @Inject
    StreamService streamService;

    @Inject
    JwtService jwtService;

    // Método fictício para obter um token JWT (substitua pela lógica real)
    //private String obterTokenJWT(StreamDTO streamDTO) 
    //{
    //    StreamResponseDTO streamResponseDTO = StreamResponseDTO.valueOf(streamDTO);
    //    return jwtService.generateJwt(streamResponseDTO);
    //}
    
    @Test
    public void testInsert() {
        // Crie um usuário fictício para o teste
        StreamDTO streamDTO = new StreamDTO(
            "gusta", 
            (long) 2,
            2.0F
            );

        // Gere um token JWT para o stream fictício (use sua lógica real para obter o token)
        //String tokenJWT = obterTokenJWT(streamDTO);

        // Adicione o token JWT aos cabeçalhos da solicitação
            given()
                //.auth().oauth2(tokenJWT)
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when().post("/stream")
                .then()
                .statusCode(404)
                //.body(
                //"nome", is("gusta insert"),
                //"idUsuario", is(2),
                //"custoStream:", is (23),
                //"senha:", is ("insertPassword"),
                //"idPerfil:", is (1),
                //"listaTelefone:", is (null))
                ;
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        StreamDTO streamDTO = new StreamDTO(
            "gusta", 
            (long) 2,
            2.0F
            );

        given()
            .contentType(ContentType.JSON)
            .body(streamDTO)
            .when()
            .put("/stream/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .delete("/stream/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindAll() {
        given()
            .when()
            .get("/stream")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .get("/stream/1")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        // Assumindo que o nome "Gusta" existe no banco de dados
        given()
            .when()
            .get("/stream/search/nome/Gusta")
            .then()
            .statusCode(404);
    }
}
