package br.unitins.topicos1;
import br.unitins.topicos1.dto.Login.LoginDTO;
import br.unitins.topicos1.dto.Usuario.UsuarioResponseDTO;
import br.unitins.topicos1.services.Hash.HashService;
import br.unitins.topicos1.services.Jwt.JwtService;
import br.unitins.topicos1.services.Usuario.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import br.unitins.topicos1.dto.Stream.StreamDTO;
import br.unitins.topicos1.services.Stream.StreamService;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class StreamResourceTest {
    @Inject
    StreamService streamService;

    @Inject
    JwtService jwtService;

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    // Token User: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6Im11c2siLCJncm91cHMiOlsiVXNlciJdLCJleHAiOjE3MDI2NjY5ODksImlhdCI6MTcwMjU4MDU5MCwianRpIjoiMjRlNWQwMTUtZjQyMS00Y2E4LTg1NzgtZTBmMDU4NjVkZjAyIn0.ibxJ-2OtU0UNDIRQmnnxtQQ_l9sg0yICeP_iwt_msMP-RSNdfkcEif2TI_I6R-EJTLJ6NGnA9f5mR-toUoe2kjtnuuO-KWYHmEdVmx74I8_EAr_2T3VNqJ8OiaX6UIkEJtV64MwkJDAq8AZ8juSyH60CwWUseLKq4n433tziLj3ckEAPssw-tj7HBLXNdBHeW5X_CSaB6ZgDCIbA0NZJ7upk0qq6kdZVO9YpRcv-_V8-lBgWnJ1ZJ0ye_VpMC2mZUCTyNG5fVILurlfinH7JZJoa3iF9grSHkLUCX1-vbyQH5T9ORlCZQjpWRZoZzbTJC27kyV2i-YkOTAr-kqgLSQ
    // Token Adm: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImdhdGVzIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjY2NzI3NSwiaWF0IjoxNzAyNTgwODc1LCJqdGkiOiIzM2E5NmZlNC00Yjc4LTQ0YTYtODA3MC02NzJmMWJiNDlhODkifQ.bbs5hzguRYpzivWbwJUH0K3EJYFhpVCzvr0p8aaQOrOrgJb7ytwUWj2Fxt2xdRSchIYObWsDwmk743JJDdta-WBwIHXkX2ilpRl5u9oUHA8DCcn8YJkmgfUhoMsQI9_ysA5s-flkXcEKHYnW20dWSDizc-_3PC2WShYw6kPdiOkpp_N7vmYf0IXAfZImvt1iJ-azkY5JctuHRB7aAAHNVxSlK8bdEVp_pWt4Sc3TcsZDscNzo-xvFaXwD5jZxUf8rzn-WbpdNY7Qwnkefpyo2A0iFPif9j32-cgRTChByuA-UwAM8nLJo3fbQ3cZk5tqqx9Hn2OuHKlrLwhxCgRjuA
    // Token Streamer: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkpWIHN0cm8iLCJncm91cHMiOlsic3RyZWFtZXIiXSwiZXhwIjoxNzAyNjY3MTU3LCJpYXQiOjE3MDI1ODA3NTcsImp0aSI6IjM1MWZhZWU2LWVmYTQtNDVjYi1hNjRmLWFmZTI5MTExZTg4OCJ9.Z8zRMxKhm-Az3jOMmFpYxhCF0KdKeTfJleysVORA-w_FcKteqzTvBPH0lUErPjLvUibogMML2vHoZdZ0UvINm2ImdGyyUQQE0WzoAOTFq8i6zzQnpvdNIyAlQQzG-pHFPP9IVDJMG7Olw8x3PUpTnIP7J5aonLMi0v2gGNgE0NX62qg8uGvxOSaimV82odjKd_GdcWngHy-UCvmfinXHIRvJmlxECt5DvgNtylc97Hdxu0Wzz_l9u20O5cdFnWdVG3HIPbodNzUDgwahjn07xtevquZvgofkKp4bPXtg4rCgqBFwS5RQ2XM1hSTDSSIXlvkFDq1X-vt0CuyCgd0ZlA

    @Test
    public void testInsert() {
        // Crie um usuário fictício para o teste
        StreamDTO streamDTO = new StreamDTO(
            "gusta", 
            (long) 2,
            2.0F
            );
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);
        // Adicione o token JWT aos cabeçalhos da solicitação
            given().headers("Authorization", "Bearer " + tokenStreamer)
                .contentType(ContentType.JSON)
                .body(streamDTO)
                .when().post("/streams")
                .then()
                .statusCode(201);
    }

    @Test
    public void testUpdate() {
        // Assumindo que o ID 1 existe no banco de dados
        StreamDTO streamDTO = new StreamDTO(
            "gusta", 
            (long) 2,
            2.0F
            );

        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        // Adicione o token JWT aos cabeçalhos da solicitação
        given().headers("Authorization", "Bearer " + tokenStreamer)
            .contentType(ContentType.JSON)
            .body(streamDTO)
            .when()
            .put("/streams/2")
            .then()
            .statusCode(200);
    }

    @Test
    public void testDelete() {
        // Assumindo que o ID 1 existe no banco de dados
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        // Adicione o token JWT aos cabeçalhos da solicitação
        given().headers("Authorization", "Bearer " + tokenStreamer)
            .when()
            .delete("/streams/1")
            .then()
            .statusCode(204);
    }

    @Test
    public void testFindAll() {
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        // Adicione o token JWT aos cabeçalhos da solicitação
        given().headers("Authorization", "Bearer " + tokenStreamer)
            .when()
            .get("/streams")
            .then()
            .statusCode(200);
    }

    @Test
    public void testFindById() {
        // Assumindo que o ID 1 existe no banco de dados
        LoginDTO loginDTO = new LoginDTO("JV stro", "senha3");
        String hashSenha = hashService.getHashSenha(loginDTO.senha());
        UsuarioResponseDTO result = usuarioService.findByLoginAndSenha(loginDTO.login(), hashSenha.toString());
        String tokenStreamer = jwtService.generateJwt(result);

        // Adicione o token JWT aos cabeçalhos da solicitação
        given().headers("Authorization", "Bearer " + tokenStreamer)
            .when()
            .get("/streams/1")
            .then()
            .statusCode(200);
    }
}
