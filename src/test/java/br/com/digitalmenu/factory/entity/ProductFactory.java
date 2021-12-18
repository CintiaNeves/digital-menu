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
        return Product.builder()
            .description("x-bacon")
            .price(15D)
            .category(FOOD)
            .ingredients("bread, cheese, bacon and salad.")
            .additional(false)
            .build();
    }

    public Product getPersistedProduct() {
        return productRepository.save(this.getDefaultFoodProduct());
    }

}
