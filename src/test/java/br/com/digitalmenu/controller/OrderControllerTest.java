package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.factory.request.OrderRequestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.digitalmenu.domain.enums.Status.OPEN;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class OrderControllerTest {

    private final OrderRequestFactory orderRequestFactory;

    @Autowired
    public OrderControllerTest(OrderRequestFactory orderRequestFactory) {
        this.orderRequestFactory = orderRequestFactory;
    }

    @Test
    void shouldCreateOrder() {
        String BASE_URL = "/api/order/";
        given()
            .body(orderRequestFactory.getDefaultOrderRequest())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("id", notNullValue())
            .body("status", equalTo(OPEN.toString()))
            .body("datCreate", notNullValue())
            .body("datUpdate", notNullValue());
    }
}