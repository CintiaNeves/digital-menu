package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;

import static br.com.digitalmenu.domain.enums.Category.FOOD;

@Configuration
public class ProductFactory {

    private final ProductRepository productRepository;

    public ProductFactory(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getDefaultFoodProduct() {
        Product product = new Product();
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

    public Customer getEmpty(){
        return new Customer();
    }
}
