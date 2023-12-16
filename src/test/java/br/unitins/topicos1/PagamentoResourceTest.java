package br.unitins.topicos1;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Pagamento.CartaoCredito.CartaoCreditoDTO;
import br.unitins.topicos1.dto.Pagamento.Pix.PixDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.Hash.HashService;
import br.unitins.topicos1.service.Jwt.JwtService;
import br.unitins.topicos1.service.Usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PagamentoResourceTest {

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;
    private static final Logger LOG = Logger.getLogger(PagamentoResourceTest.class);

    @Test
    public void testPagamentoSolicitacaoPix() {
        // Crie um cadastro fictício para o teste
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(1L);

        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);

        LoginDTO loginDTO = new LoginDTO("musk", "senha1");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenUser = jwtService.generateJwt(result);

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .body(duoDTO)
                .when().post("/duos")
                .then()
                .statusCode(201)
        ;

        // Aqui entendendo que o duo 1 foi criado, a solicitação 1 foi encontrada no banco de dados;

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .when().get("/solicitacoes/1")
                .then()
                .statusCode(200)
        ;

        PixDTO pixDTO = new PixDTO(1L, 1);
        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .body(pixDTO)
                .when().post("pagamento/pix")
                .then()
                .statusCode(201)
        ;
    }

    @Test
    public void testPagamentoSolicitacaoCartao() {
        // Crie um cadastro fictício para o teste
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(2L);

        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);

        LoginDTO loginDTO = new LoginDTO("musk", "senha1");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenUser = jwtService.generateJwt(result);

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .body(duoDTO)
                .when().post("/duos")
                .then()
                .statusCode(201)
        ;

        // Aqui entendendo que o duo 1 foi criado, a solicitação 1 foi encontrada no banco de dados;

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .when().get("/solicitacoes/1")
                .then()
                .statusCode(200)
        ;

        CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO(1L, "123", "Joao", LocalDate.now(), "123", 1);
        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenUser)
                .body(cartaoCreditoDTO)
                .when().post("pagamento/cartao")
                .then()
                .statusCode(201)
        ;
    }
}
