package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.factory.CustomerFactory;
import br.com.digitalmenu.factory.CustomerRequestFactory;
import br.com.digitalmenu.repository.CustomerRepository;
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

    private final CustomerRepository repository;
    private final CustomerRequestFactory customerRequestFactory;
    private final CustomerFactory factory;
    private final String BASE_URL = "/api/customer/";

    @Autowired
    public CustomerControllerTest(CustomerRepository repository,
                                  CustomerRequestFactory customerRequestFactory,
                                  CustomerFactory factory) {
        this.repository = repository;
        this.customerRequestFactory = customerRequestFactory;
        this.factory = factory;
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    void shouldCreateACustomer() {
        given()
            .body(customerRequestFactory.getDefaultClientRequest())
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
        repository.save(factory.getDefaultClient());

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
        Customer customer = repository.save(factory.getDefaultClient());
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
        Customer customer = repository.save(factory.getDefaultClient());
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
    @DisplayName("Deve buscar um cliente pelo nome")
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