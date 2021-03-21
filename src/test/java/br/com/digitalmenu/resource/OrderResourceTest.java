package br.com.digitalmenu.resource;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.request.OrderStatusRequest;
import br.com.digitalmenu.factory.OrderFactory;
import br.com.digitalmenu.factory.OrderRequestFactory;
import br.com.digitalmenu.factory.OrderStatusRequestFactory;
import br.com.digitalmenu.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.digitalmenu.domain.enums.Status.DELIVERED;
import static br.com.digitalmenu.domain.enums.Status.OPEN;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class OrderResourceTest {
    private final transient OrderRequestFactory orderRequestFactory;
    private final transient OrderRepository orderRepository;
    private final transient OrderFactory orderFactory;
    private final transient OrderStatusRequestFactory orderStatusRequestFactory;
    private final transient String baseUrl = "/api/order/";

    @Autowired
    public OrderResourceTest(OrderRequestFactory orderRequestFactory,
                             OrderRepository orderRepository,
                             OrderFactory orderFactory,
                             OrderStatusRequestFactory orderStatusRequestFactory) {
        this.orderRequestFactory = orderRequestFactory;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderStatusRequestFactory = orderStatusRequestFactory;
    }

    @Test
    void shouldCreateOrder() {
        given()
            .body(orderRequestFactory.getDefaultOrderRequest())
        .when()
            .post(baseUrl)
        .then()
            .statusCode(SC_OK)
            .body("id", notNullValue())
            .body("status", equalTo(OPEN.toString()))
            .body("datCreate", notNullValue())
            .body("datUpdate", notNullValue());
    }

    @Test
    void shouldGetAllOrders() {
        orderRepository.save(orderFactory.getDefaultOrder());
        given()
        .when()
            .get(baseUrl)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    void shouldReturnOrderByClientId() {
        Orders order = orderRepository.save(orderFactory.getDefaultOrder());
        given()
        .when()
            .get(baseUrl + order.getClient().getId())
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    void shouldUpdateOrder() {
        Orders order = orderRepository.save(orderFactory.getDefaultOrder());
        Long idOrderSaved = order.getId();
        OrderStatusRequest request = orderStatusRequestFactory.getEmpty();
        request.setStatus(DELIVERED);
        given()
            .body(request)
        .when()
            .put(baseUrl + idOrderSaved)
        .then()
            .statusCode(SC_OK)
            .body("status", equalTo(DELIVERED.toString()));
    }
}