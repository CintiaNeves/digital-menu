package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.factory.CustomerFactory;
import br.com.digitalmenu.factory.CustomerRequestFactory;
import br.com.digitalmenu.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class CustomerResourceTest {

    private final CustomerRepository repository;
    private final CustomerRequestFactory customerRequestFactory;
    private final CustomerFactory factory;
    private final String BASE_URL = "/api/customer/";

    @Autowired
    public CustomerResourceTest(CustomerRepository repository,
                                CustomerRequestFactory customerRequestFactory,
                                CustomerFactory factory) {
        this.repository = repository;
        this.customerRequestFactory = customerRequestFactory;
        this.factory = factory;
    }

    @Test
    void shouldCreateClient() {
        given()
            .body(customerRequestFactory.getDefaultClientRequest())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_OK)
            .body("id", notNullValue())
            .body("datCreate", notNullValue())
            .body("datUpdate", notNullValue());
    }

    @Test
    void shouldGetAllClients() {
        repository.save(factory.getDefaultClient());
        given()
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    void shouldReturnClientById() {
        Customer customer = repository.save(factory.getDefaultClient());
        given()
        .when()
            .get(BASE_URL + customer.getId())
        .then()
            .statusCode(SC_OK)
            .body(containsString("Nome Completo do Cliente"));
    }

    @Test
    void shouldReturnBadRequestWhenTryCreateDuplicatedClientPhone() {
        Customer customer = repository.save(factory.getDefaultClient());
        CustomerRequest request = customerRequestFactory.getDefaultClientRequest();
        request.setPhone(customer.getPhone());
        given()
            .body(request)
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    void shouldUpdateClient() {
        Customer customer = repository.save(factory.getDefaultClient());
        Long idClientSaved = customer.getId();
        CustomerRequest request = customerRequestFactory.getDefaultClientRequest();
        request.setName("Nome Completo Alterado");
        request.setPhone("00000000000");
        request.setEmail("email.alterado@email.com");
        given()
            .body(request)
        .when()
            .put(BASE_URL + idClientSaved)
        .then()
            .statusCode(SC_OK)
            .body("name", containsString("Alterado"))
            .body("phone", equalTo("00000000000"))
            .body("email", containsString("alterado"));
    }

    @Test
    void shouldDeleteClient() {
        Customer customer = repository.save(factory.getDefaultClient());
        Long idClientSaved = customer.getId();
        given()
        .when()
            .delete(BASE_URL + idClientSaved)
        .then()
            .statusCode(SC_NO_CONTENT);
    }

    @Test
    void shouldSearchClientByName(){
        repository.save(factory.getDefaultClient());
        given()
        .when()
            .get(BASE_URL + "search/Nome")
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }
}