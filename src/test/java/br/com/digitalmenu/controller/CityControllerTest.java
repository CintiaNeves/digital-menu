package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.factory.entity.CityFactory;
import br.com.digitalmenu.factory.request.CityRequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class CityControllerTest {

    private final CityRequestFactory requestFactory;

    private final CityFactory cityFactory;

    private final String BASE_URL = "/api/city/";

    @Autowired
    public CityControllerTest(CityRequestFactory requestFactory, CityFactory cityFactory) {
        this.requestFactory = requestFactory;
        this.cityFactory = cityFactory;
    }

    @Test
    @DisplayName("Deve salvar uma cidade")
    void shouldCreateCity() {
        given()
            .body(requestFactory.getDefaultCityRequest())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("name", notNullValue());
    }

    @Test
    @DisplayName("Deve listar todas as cidades")
    void shouldListAllCities() {
        cityFactory.getPersistedCity();
        given()
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    @DisplayName("Deve buscar uma cidade por ID")
    void shouldGetACityById() {
        var city = cityFactory.getPersistedCity();

        given()
        .when()
            .get(BASE_URL + city.getId())
        .then()
            .statusCode(SC_OK)
            .body("name", notNullValue());
    }

    @Test
    @DisplayName("Deve ser idempontente no método de criação")
    void shouldBeIdempotentWhenCreateMethod() {
        var city = cityFactory.getPersistedCity();
        var cityRequest = requestFactory.getDefaultCityRequest();
        given()
            .body(cityRequest)
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("name", equalTo(city.getName()));
    }
}
