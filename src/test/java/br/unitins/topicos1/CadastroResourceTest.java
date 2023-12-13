package br.unitins.topicos1;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.CadastroDTO;
import br.unitins.topicos1.service.CadastroService;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.UsuarioService;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import br.unitins.topicos1.service.JwtService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class CadastroResourceTest {
    @Inject
    CadastroService cadastroService;

    @Inject
    JwtService jwtService;
    
    @Test
    public void testInsert() {
        // Crie um cadastro fictício para o teste
        CadastroDTO cadatroDTO = new CadastroDTO(
            "Digo Alen", 
            "digo.alen", 
            "senha456", 
            "87623454398",
            null);

            given()
                .contentType(ContentType.JSON)
                .body(cadatroDTO)
                .when().post("/cadastro")
                .then()
                .statusCode(201)
                //.body(
                //"nome", is("Digo insert"),
                //"login", is("digo.insert"),
                //"senha:", is ("insertPassword"),
                //"cpf:", is ("87623454398"),
                //"listaTelefone:", is (null))
                ;
    }
}
