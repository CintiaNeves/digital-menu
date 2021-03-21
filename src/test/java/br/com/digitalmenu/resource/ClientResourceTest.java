package br.com.digitalmenu.resource;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.factory.ClientFactory;
import br.com.digitalmenu.factory.ClientRequestFactory;
import br.com.digitalmenu.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class ClientResourceTest {
    private final transient ClientRepository repository;
    private final transient ClientRequestFactory clientRequestFactory;
    private final transient ClientFactory factory;
    private final transient String baseUrl = "/api/client/";

    @Autowired
    public ClientResourceTest(ClientRepository repository, ClientRequestFactory clientRequestFactory, ClientFactory factory) {
        this.repository = repository;
        this.clientRequestFactory = clientRequestFactory;
        this.factory = factory;
    }

    @Test
    void shouldCreateClient() {
        given()
            .body(clientRequestFactory.getDefaultClientRequest())
        .when()
            .post(baseUrl)
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
            .get(baseUrl)
        .then()
            .statusCode(SC_OK)
            .body("size()", not(0));
    }

    @Test
    void shouldReturnClientById() {
        Client client = repository.save(factory.getDefaultClient());
        given()
        .when()
            .get(baseUrl + client.getId())
        .then()
            .statusCode(SC_OK)
            .body(containsString("Nome Completo do Cliente"));
    }

    @Test
    void shouldReturnBadRequestWhenTryCreateDuplicatedClientPhone() {
        Client client = repository.save(factory.getDefaultClient());
        ClientRequest request = clientRequestFactory.getDefaultClientRequest();
        request.setPhone(client.getPhone());
        given()
            .body(request)
        .when()
            .post(baseUrl)
        .then()
            .statusCode(SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    void shouldUpdateClient() {
        Client client = repository.save(factory.getDefaultClient());
        Long idClientSaved = client.getId();
        ClientRequest request = clientRequestFactory.getDefaultClientRequest();
        request.setName("Nome Completo Alterado");
        request.setPhone("00000000000");
        request.setEmail("email.alterado@email.com");
        given()
            .body(request)
        .when()
            .put(baseUrl + idClientSaved)
        .then()
            .statusCode(SC_OK)
            .body("name", containsString("Alterado"))
            .body("phone", equalTo("00000000000"))
            .body("email", containsString("alterado"));
    }

    @Test
    void shouldDeleteClient() {
        Client client = repository.save(factory.getDefaultClient());
        Long idClientSaved = client.getId();
        given()
        .when()
            .delete(baseUrl + idClientSaved)
        .then()
            .statusCode(SC_NO_CONTENT);
    }

    @Test
    void shouldSearchClientByName(){
        repository.save(factory.getDefaultClient());
        given()
        .when()
            .get(baseUrl + "search/Nome")
        .then()
            .statusCode(SC_OK)
            .body("size()", not(0));
    }
}