package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.factory.CityFactory;
import br.com.digitalmenu.factory.CityRequestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

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
    void shouldCreateCity() {
        given()
            .body(requestFactory.getDefaultCityRequest())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED);
    }

    @Test
    void shouldListAllCities() {
        given()
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(SC_OK);
    }

    @Test
    void shouldGetACityById() {
        var city = cityFactory.getPersistedCity();
        given()
        .when()
            .get(BASE_URL.concat(String.valueOf(city.getId())))
        .then()
            .statusCode(SC_OK);
    }
}
