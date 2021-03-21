package br.com.digitalmenu.resource;

import br.com.digitalmenu.annotation.RestAssuredTest;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.factory.ProductFactory;
import br.com.digitalmenu.factory.ProductRequestFactory;
import br.com.digitalmenu.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.digitalmenu.domain.enums.Category.DRINKS;
import static br.com.digitalmenu.domain.enums.Category.FOOD;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RestAssuredTest
public class ProductResourceTest {
    private final transient ProductRequestFactory factory;
    private final transient ProductFactory productFactory;
    private final transient ProductRepository repository;
    private final transient String baseUrl = "/api/product/";

    @Autowired
    public ProductResourceTest(ProductRepository repository, ProductRequestFactory factory, ProductFactory productFactory) {
        this.repository = repository;
        this.factory = factory;
        this.productFactory = productFactory;
    }

    @Test
    void shouldCreateFoodProduct() {
        given()
            .body(factory.getDefaultFoodProduct())
        .when()
            .post(baseUrl)
        .then()
            .statusCode(SC_CREATED)
            .body("id", notNullValue())
            .body("category", equalTo(FOOD.toString()))
            .body("datCreate", notNullValue())
            .body("datUpdate", notNullValue());
    }

    @Test
    void shouldCreateDrinkProduct() {
        given()
            .body(factory.getDefaultDrinkProduct())
        .when()
            .post(baseUrl)
        .then()
            .statusCode(SC_CREATED)
            .body("id", notNullValue())
            .body("category", equalTo(DRINKS.toString()))
            .body("datCreate", notNullValue())
            .body("datUpdate", notNullValue());
    }

    @Test
    void shouldGetAllProducts() {
        repository.save(productFactory.getDefaultFoodProduct());
        given()
        .when()
            .get(baseUrl)
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }

    @Test
    void shouldReturnProductById() {
        Product product = repository.save(productFactory.getDefaultFoodProduct());
        given()
        .when()
            .get(baseUrl + product.getId())
        .then()
            .statusCode(SC_OK)
            .body(containsString("x-bacon"));
    }

    @Test
    void shouldReturnBadRequestWhenTryCreateDuplicatedProductDescription() {
        Product product = repository.save(productFactory.getDefaultFoodProduct());
        ProductRequest request = factory.getDefaultFoodProduct();
        request.setDescription(product.getDescription());
        given()
            .body(request)
        .when()
            .post(baseUrl)
        .then()
            .statusCode(SC_BAD_REQUEST);
    }

    @Test
    void shouldUpdateProduct() {
        Product product = repository.save(productFactory.getDefaultFoodProduct());
        Long idProductSaved = product.getId();
        ProductRequest request = factory.getEmpty();
        request.setDescription("suco laranja natural");
        request.setAdditional(false);
        request.setIngredients("laranja");
        request.setCategory(DRINKS);
        request.setPrice(8D);

        given()
            .body(request)
        .when()
            .put(baseUrl + idProductSaved)
        .then()
            .statusCode(SC_OK)
            .body("description", containsString("suco laranja natural"))
            .body("category", equalTo(DRINKS.toString()))
            .body("ingredients", containsString("laranja"));
    }

    @Test
    void shouldDeleteProduct() {
        Product product = repository.save(productFactory.getDefaultFoodProduct());
        Long idProductSaved = product.getId();

        given()
        .when()
            .delete(baseUrl + idProductSaved)
        .then()
            .statusCode(SC_NO_CONTENT);
    }

    @Test
    void shouldSearchProductByDescription(){
        repository.save(productFactory.getDefaultFoodProduct());
        given()
        .when()
            .get(baseUrl + "search/bacon")
        .then()
            .statusCode(SC_OK)
            .body("size()", equalTo(1));
    }
}