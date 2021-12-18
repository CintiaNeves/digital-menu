package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.ProductRequest;
import org.springframework.stereotype.Component;

import static br.com.digitalmenu.domain.enums.Category.DRINKS;
import static br.com.digitalmenu.domain.enums.Category.FOOD;

@Component
public class ProductRequestFactory {

    public ProductRequest getDefaultFoodProduct() {
        return ProductRequest.builder()
            .description("x-salad")
            .price(15D)
            .category(FOOD)
            .ingredients("bread, cheese, meat and salad.")
            .additional(false)
            .build();
    }

    public ProductRequest getDefaultDrinkProduct() {
        return ProductRequest.builder()
                .description("coca-cola")
                .price(5D)
                .category(DRINKS)
                .ingredients("industrialized.")
                .additional(false)
                .build();
    }

    public ProductRequest getEmpty(){
        return new ProductRequest();
    }
}
