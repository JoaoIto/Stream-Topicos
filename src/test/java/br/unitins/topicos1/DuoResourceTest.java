package br.unitins.topicos1;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.service.Hash.HashService;
import br.unitins.topicos1.service.Jwt.JwtService;
import br.unitins.topicos1.service.Usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DuoResourceTest {
    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(DuoResourceTest.class);

    @Test
    public void testInsert() {
        // Crie um cadastro fictício para o teste
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(1L);
        DuoDTO duoDTO = new DuoDTO(2L, listGames, 2);

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
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(2L);
        DuoDTO duoDTO = new DuoDTO(3L, listGames, 2);
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenStreamer)
                .body(duoDTO)
                .when().post("/duos")
                .then()
                .statusCode(201);

        given().headers("Authorization", "Bearer " + tokenStreamer)
                .contentType(ContentType.JSON)
                .body(duoDTO)
                .when()
                .put("/duos/1")
                .then()
                .statusCode(200);
    }

    // -> Acho justo não ter o método de delete em duo, já que gerando a classe de registro de solicitação, ele apagaria o registro de lá, postando não ficaria semãntico...
//    @Test
//    public void testDelete() {
//        // Assumindo que o ID 1 existe no banco de dados
//        List<Long> listGames = new ArrayList<>(3);
//        listGames.add(1L);
//        listGames.add(2L);
//        listGames.add(3L);
//        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);
//        String tokenStreamer = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkpWIHN0cm8iLCJncm91cHMiOlsic3RyZWFtZXIiXSwiZXhwIjoxNzAyNjY3MTU3LCJpYXQiOjE3MDI1ODA3NTcsImp0aSI6IjM1MWZhZWU2LWVmYTQtNDVjYi1hNjRmLWFmZTI5MTExZTg4OCJ9.Z8zRMxKhm-Az3jOMmFpYxhCF0KdKeTfJleysVORA-w_FcKteqzTvBPH0lUErPjLvUibogMML2vHoZdZ0UvINm2ImdGyyUQQE0WzoAOTFq8i6zzQnpvdNIyAlQQzG-pHFPP9IVDJMG7Olw8x3PUpTnIP7J5aonLMi0v2gGNgE0NX62qg8uGvxOSaimV82odjKd_GdcWngHy-UCvmfinXHIRvJmlxECt5DvgNtylc97Hdxu0Wzz_l9u20O5cdFnWdVG3HIPbodNzUDgwahjn07xtevquZvgofkKp4bPXtg4rCgqBFwS5RQ2XM1hSTDSSIXlvkFDq1X-vt0CuyCgd0ZlA";
//
//        given()
//                .contentType(ContentType.JSON)
//                .headers("Authorization", "Bearer " + tokenStreamer)
//                .body(duoDTO)
//                .when().post("/duos")
//                .then()
//                .statusCode(201);
//        String tokenAdm = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImdhdGVzIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjY2NzI3NSwiaWF0IjoxNzAyNTgwODc1LCJqdGkiOiIzM2E5NmZlNC00Yjc4LTQ0YTYtODA3MC02NzJmMWJiNDlhODkifQ.bbs5hzguRYpzivWbwJUH0K3EJYFhpVCzvr0p8aaQOrOrgJb7ytwUWj2Fxt2xdRSchIYObWsDwmk743JJDdta-WBwIHXkX2ilpRl5u9oUHA8DCcn8YJkmgfUhoMsQI9_ysA5s-flkXcEKHYnW20dWSDizc-_3PC2WShYw6kPdiOkpp_N7vmYf0IXAfZImvt1iJ-azkY5JctuHRB7aAAHNVxSlK8bdEVp_pWt4Sc3TcsZDscNzo-xvFaXwD5jZxUf8rzn-WbpdNY7Qwnkefpyo2A0iFPif9j32-cgRTChByuA-UwAM8nLJo3fbQ3cZk5tqqx9Hn2OuHKlrLwhxCgRjuA";
//
//        given().headers("Authorization", "Bearer " + tokenAdm)
//                .contentType(ContentType.JSON)
//                .body(duoDTO)
//                .when()
//                .delete("/duos/1")
//                .then()
//                .statusCode(204);
//    }

    @Test
    public void testFindAll() {
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        // Adicione o token JWT aos cabeçalhos da solicitação
        given().headers("Authorization", "Bearer " + tokenStreamer)
                .contentType(ContentType.JSON)
                .when()
                .get("/duos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindById() {
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(1L);
        listGames.add(2L);
        listGames.add(3L);
        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);

        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenStreamer)
                .body(duoDTO)
                .when().post("/duos")
                .then()
                .statusCode(201);

        given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + tokenStreamer)
                .when()
                .get("/duos/1")
                .then()
                .statusCode(200);
        ;
    }
}
