package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.stereotype.Component;

import static br.com.digitalmenu.domain.enums.Category.FOOD;

@Component
public class ProductFactory {

    private final ProductRepository productRepository;

    public ProductFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getDefaultFoodProduct() {
        var product = new Product();
        product.setDescription("x-bacon");
        product.setPrice(15D);
        product.setCategory(FOOD);
        product.setIngredients("bread, cheese, bacon and salad.");
        product.setAdditional(false);
        return product;
    }

    public Product getPersistedProduct() {
        return productRepository.save(this.getDefaultFoodProduct());
    }

}
