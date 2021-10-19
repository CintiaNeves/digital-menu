package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.ProductRequest;
import org.springframework.stereotype.Component;

import static br.com.digitalmenu.domain.enums.Category.DRINKS;
import static br.com.digitalmenu.domain.enums.Category.FOOD;

@Component
public class ProductRequestFactory {

    public ProductRequest getDefaultFoodProduct() {
        var productRequest = new ProductRequest();
        productRequest.setDescription("x-salad");
        productRequest.setPrice(15D);
        productRequest.setCategory(FOOD);
        productRequest.setIngredients("bread, cheese, meat and salad.");
        productRequest.setAdditional(false);
       return productRequest;
    }

    public ProductRequest getDefaultDrinkProduct() {
        var productRequest = new ProductRequest();
        productRequest.setDescription("coca-cola");
        productRequest.setPrice(5D);
        productRequest.setCategory(DRINKS);
        productRequest.setIngredients("industrialized");
        productRequest.setAdditional(false);
        return productRequest;
    }

    public ProductRequest getEmpty(){
        return new ProductRequest();
    }
}
