package br.unitins.topicos1;

import br.unitins.topicos1.dto.Duo.DuoDTO;
import br.unitins.topicos1.repository.DuoRepository;
import br.unitins.topicos1.repository.GameRepository;
import br.unitins.topicos1.repository.StreamRepository;
import br.unitins.topicos1.service.Duo.DuoService;
import br.unitins.topicos1.service.Solicitacoes.SolicitacoesService;
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
public class SolicitacoesResourceTest {

    @Inject
    SolicitacoesService service;

    @Inject
    DuoService duoService;

    @Inject
    DuoRepository duoRepository;
    @Inject
    GameRepository gameRepository;

    @Inject
    StreamRepository streamRepository;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(SolicitacoesResourceTest.class);

    // Token User: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6Im11c2siLCJncm91cHMiOlsiVXNlciJdLCJleHAiOjE3MDI2NjY5ODksImlhdCI6MTcwMjU4MDU5MCwianRpIjoiMjRlNWQwMTUtZjQyMS00Y2E4LTg1NzgtZTBmMDU4NjVkZjAyIn0.ibxJ-2OtU0UNDIRQmnnxtQQ_l9sg0yICeP_iwt_msMP-RSNdfkcEif2TI_I6R-EJTLJ6NGnA9f5mR-toUoe2kjtnuuO-KWYHmEdVmx74I8_EAr_2T3VNqJ8OiaX6UIkEJtV64MwkJDAq8AZ8juSyH60CwWUseLKq4n433tziLj3ckEAPssw-tj7HBLXNdBHeW5X_CSaB6ZgDCIbA0NZJ7upk0qq6kdZVO9YpRcv-_V8-lBgWnJ1ZJ0ye_VpMC2mZUCTyNG5fVILurlfinH7JZJoa3iF9grSHkLUCX1-vbyQH5T9ORlCZQjpWRZoZzbTJC27kyV2i-YkOTAr-kqgLSQ
    // Token Adm: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6ImdhdGVzIiwiZ3JvdXBzIjpbIkFkbWluIl0sImV4cCI6MTcwMjY2NzI3NSwiaWF0IjoxNzAyNTgwODc1LCJqdGkiOiIzM2E5NmZlNC00Yjc4LTQ0YTYtODA3MC02NzJmMWJiNDlhODkifQ.bbs5hzguRYpzivWbwJUH0K3EJYFhpVCzvr0p8aaQOrOrgJb7ytwUWj2Fxt2xdRSchIYObWsDwmk743JJDdta-WBwIHXkX2ilpRl5u9oUHA8DCcn8YJkmgfUhoMsQI9_ysA5s-flkXcEKHYnW20dWSDizc-_3PC2WShYw6kPdiOkpp_N7vmYf0IXAfZImvt1iJ-azkY5JctuHRB7aAAHNVxSlK8bdEVp_pWt4Sc3TcsZDscNzo-xvFaXwD5jZxUf8rzn-WbpdNY7Qwnkefpyo2A0iFPif9j32-cgRTChByuA-UwAM8nLJo3fbQ3cZk5tqqx9Hn2OuHKlrLwhxCgRjuA
    // Token Streamer: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkpWIHN0cm8iLCJncm91cHMiOlsic3RyZWFtZXIiXSwiZXhwIjoxNzAyNjY3MTU3LCJpYXQiOjE3MDI1ODA3NTcsImp0aSI6IjM1MWZhZWU2LWVmYTQtNDVjYi1hNjRmLWFmZTI5MTExZTg4OCJ9.Z8zRMxKhm-Az3jOMmFpYxhCF0KdKeTfJleysVORA-w_FcKteqzTvBPH0lUErPjLvUibogMML2vHoZdZ0UvINm2ImdGyyUQQE0WzoAOTFq8i6zzQnpvdNIyAlQQzG-pHFPP9IVDJMG7Olw8x3PUpTnIP7J5aonLMi0v2gGNgE0NX62qg8uGvxOSaimV82odjKd_GdcWngHy-UCvmfinXHIRvJmlxECt5DvgNtylc97Hdxu0Wzz_l9u20O5cdFnWdVG3HIPbodNzUDgwahjn07xtevquZvgofkKp4bPXtg4rCgqBFwS5RQ2XM1hSTDSSIXlvkFDq1X-vt0CuyCgd0ZlA

    @Test
    public void testInsertDuoAndGet() {
        // Crie um cadastro fictício para o teste
        List<Long> listGames = new ArrayList<>(3);
        listGames.add(1L);
        listGames.add(2L);
        listGames.add(3L);
        DuoDTO duoDTO = new DuoDTO(1L, listGames, 2);
        String tokenUser = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6Im11c2siLCJncm91cHMiOlsiVXNlciJdLCJleHAiOjE3MDI2NjY5ODksImlhdCI6MTcwMjU4MDU5MCwianRpIjoiMjRlNWQwMTUtZjQyMS00Y2E4LTg1NzgtZTBmMDU4NjVkZjAyIn0.ibxJ-2OtU0UNDIRQmnnxtQQ_l9sg0yICeP_iwt_msMP-RSNdfkcEif2TI_I6R-EJTLJ6NGnA9f5mR-toUoe2kjtnuuO-KWYHmEdVmx74I8_EAr_2T3VNqJ8OiaX6UIkEJtV64MwkJDAq8AZ8juSyH60CwWUseLKq4n433tziLj3ckEAPssw-tj7HBLXNdBHeW5X_CSaB6ZgDCIbA0NZJ7upk0qq6kdZVO9YpRcv-_V8-lBgWnJ1ZJ0ye_VpMC2mZUCTyNG5fVILurlfinH7JZJoa3iF9grSHkLUCX1-vbyQH5T9ORlCZQjpWRZoZzbTJC27kyV2i-YkOTAr-kqgLSQ";

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
    }
}