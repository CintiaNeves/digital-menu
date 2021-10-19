package br.com.digitalmenu.controller;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.factory.entity.ProductFactory;
import br.com.digitalmenu.factory.request.ProductRequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.digitalmenu.domain.enums.Category.DRINKS;
import static br.com.digitalmenu.domain.enums.Category.FOOD;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@RestAssuredTest
public class ProductControllerTest {

    private final ProductRequestFactory requestFactory;

    private final ProductFactory productFactory;

    private final String BASE_URL = "/api/product/";

    @Autowired
    public ProductControllerTest(ProductRequestFactory requestFactory, ProductFactory productFactory) {
        this.requestFactory = requestFactory;
        this.productFactory = productFactory;
    }

    @Test
    @DisplayName("Deve criar um produto do tipo FOOD")
    void shouldCreateFoodProduct() {
        given()
            .body(requestFactory.getDefaultFoodProduct())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("category", equalTo(FOOD.toString()));
    }

    @Test
    @DisplayName("Deve criar um produto do tipo DRINKS")
    void shouldCreateDrinkProduct() {
        given()
            .body(requestFactory.getDefaultDrinkProduct())
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(SC_CREATED)
            .body("category", equalTo(DRINKS.toString()));
    }

    @Test
    @DisplayName("Deve listar todos os produtos do banco")
    void shouldGetAllProducts() {
        var product = productFactory.getPersistedProduct();

        given()
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    @DisplayName("Deve buscar um produto pelo ID")
    void shouldReturnProductById() {
        var product = productFactory.getPersistedProduct();

        given()
        .when()
            .get(BASE_URL + product.getId())
        .then()
            .statusCode(SC_OK)
            .body(containsString("x-bacon"));
    }

    @Test
    @DisplayName("Deve atualizar os dados de um produto no banco")
    void shouldUpdateProduct() {
        var product = productFactory.getPersistedProduct();

        Long idProductSaved = product.getId();
        ProductRequest request = requestFactory.getEmpty();
        request.setDescription("suco laranja natural");
        request.setAdditional(false);
        request.setIngredients("laranja");
        request.setCategory(DRINKS);
        request.setPrice(8D);

        given()
            .body(request)
        .when()
            .put(BASE_URL + idProductSaved)
        .then()
            .statusCode(SC_OK)
            .body("description", containsString("suco laranja natural"))
            .body("category", equalTo(DRINKS.toString()))
            .body("ingredients", containsString("laranja"));
    }

    @Test
    @DisplayName("Deve deletar um produto no banco de dados")
    void shouldDeleteProduct() {
        var product = productFactory.getPersistedProduct();
        Long idProductSaved = product.getId();

        given()
        .when()
            .delete(BASE_URL + idProductSaved)
        .then()
            .statusCode(SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Deve buscar um produto pela descrição")
    void shouldSearchProductByDescription(){
        var product = productFactory.getPersistedProduct();

        given()
        .when()
            .get(BASE_URL + "search/bacon")
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }
}