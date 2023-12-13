package br.unitins.topicos1;

import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;
@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    // Método fictício para obter um token JWT (substitua pela lógica real)
    //private String obterTokenJWT(UsuarioDTO usuarioDTO) 
    //{
    //    UsuarioResponseDTO usuarioResponseDTO = UsuarioResponseDTO.valueOf(usuarioDTO);
    //    return jwtService.generateJwt(usuarioResponseDTO);
    //}
    
    @Test
    public void testInsert() {
        // Crie um usuário fictício para o teste
        UsuarioDTO usuarioDTO = new UsuarioDTO(
            "John Doe", 
            "john.doe", 
            "12312312312" ,
            "senha123", 
            1, 
            null);

        // Gere um token JWT para o usuário fictício (use sua lógica real para obter o token)
        //String tokenJWT = obterTokenJWT(usuarioDTO);

        // Adicione o token JWT aos cabeçalhos da solicitação
            given()
                //.auth().oauth2(tokenJWT)
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when().post("/usuarios")
                .then()
                .statusCode(401)
                //.body(
                //"nome", is("John insert"),
                //"login", is("john.insert"),
                //"cpf:", is ("12312312312"),
                //"senha:", is ("insertPassword"),
                //"idPerfil:", is (1),
                //"listaTelefone:", is (null))
                ;
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        UsuarioDTO usuarioDTO = new UsuarioDTO(
            "John Updated", 
            "john.updated",
            "12312312312", 
            "updatedPassword", 
            1, 
            null);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTO)
            .when()
            .put("/usuarios/1")
            .then()
            .statusCode(401);
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .delete("/usuarios/1")
            .then()
            .statusCode(401);
    }

    @Test
    public void testFindAll() {
        given()
            .when()
            .get("/usuarios")
            .then()
            .statusCode(401);
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        given()
            .when()
            .get("/usuarios/1")
            .then()
            .statusCode(401);
    }

    @Test
    public void testFindByNome() {
        // Assumindo que o nome "John" existe no banco de dados
        given()
            .when()
            .get("/usuarios/search/nome/John")
            .then()
            .statusCode(401);
    }
}
