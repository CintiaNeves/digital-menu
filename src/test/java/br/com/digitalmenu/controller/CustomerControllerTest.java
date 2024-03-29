package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.factory.entity.CustomerFactory;
import br.com.digitalmenu.factory.request.CustomerRequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class CustomerControllerTest {

    private final CustomerRequestFactory customerRequestFactory;

    private final CustomerFactory customerFactory;

    private final String BASE_URL = "/api/customer/";

    @Autowired
    public CustomerControllerTest(CustomerRequestFactory customerRequestFactory,
                                  CustomerFactory customerFactory) {
        this.customerRequestFactory = customerRequestFactory;
        this.customerFactory = customerFactory;
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    void shouldCreateACustomer() {
        given()
            .body(customerRequestFactory.getDefaultCustomerRequest())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("name", notNullValue())
            .body("phone", notNullValue())
            .body("email", notNullValue());
    }

    @Test
    @DisplayName("Deve listar todos os clientes")
    void shouldGetAllClients() {
        customerFactory.getPersistedCustomer();
        given()
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    @DisplayName("Deve buscar um cliente pelo ID")
    void shouldReturnClientById() {
        var customer = customerFactory.getPersistedCustomer();

        given()
        .when()
            .get(BASE_URL + customer.getId())
        .then()
            .statusCode(SC_OK)
            .body(containsString("Nome Completo do Cliente"));
    }

    @Test
    @DisplayName("Deve deletar um cliente do banco de dados")
    void shouldDeleteClient() {
        var customer = customerFactory.getPersistedCustomer();
        Long idClientSaved = customer.getId();

        given()
        .when()
            .delete(BASE_URL + idClientSaved)
        .then()
            .statusCode(SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Deve atualizar os dados do cliente no banco")
    void shouldUpdateClient() {
        var customer = customerFactory.getPersistedCustomer();
        Long idClientSaved = customer.getId();
        CustomerRequest request = customerRequestFactory.getUpdatedCustomerRequest();
        given()
            .body(request)
        .when()
            .put(BASE_URL + idClientSaved)
        .then()
            .statusCode(SC_OK)
            .body("name", equalTo(request.getName()))
            .body("phone", equalTo(request.getPhone()))
            .body("email", equalTo(request.getEmail()));
    }

    @Test
    @DisplayName("Deve buscar um cliente pelo nome")
    void shouldSearchClientByName(){
        customerFactory.getPersistedCustomer();
        given()
        .when()
            .get(BASE_URL + "search/Nome")
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    @DisplayName("Deve ser idempontente no método de criação")
    void shouldBeIdempotentWhenCreateMethod() {
        var customer = customerFactory.getPersistedCustomer();
        var customerRequest = customerRequestFactory.getDefaultCustomerRequest();
        given()
            .body(customerRequest)
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
                .body("name", equalTo(customer.getName()))
                .body("phone", equalTo(customer.getPhone()))
                .body("email", equalTo(customer.getEmail()));

    }
}