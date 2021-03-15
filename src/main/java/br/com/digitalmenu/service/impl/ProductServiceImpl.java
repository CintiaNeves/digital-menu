package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.repository.ProductRepository;
import br.com.digitalmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(ProductRequest productRequest) {

        if (findByDescription(productRequest.getDescription()).isPresent()) {
            throw new EntityAlreadyExistsException("Entity already exists.");
        }
        Product product = new Product();
        buildProduct(productRequest, product);
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return repository.findById(productId);
    }

    @Override
    public Boolean existsById(Long productId) {
        return repository.existsById(productId);
    }


    @Override
    public Product update(Long productId, ProductRequest productRequest) {

        Product product = repository.findById(productId).get();
        buildProduct(productRequest, product);

        return repository.save(product);
    }

    @Override
    public void delete(Long productId) {
        Product product = repository.findById(productId).get();
        repository.delete(product);
    }

    @Override
    public Optional<Product> findByDescription(String productDescription) {
        return repository.findByDescription(productDescription);
    }

    @Override
    public List<Product> findLikeDescription(String productDescription) {
        return repository.findByDescriptionContaining(productDescription);
    }

    private void buildProduct(ProductRequest productRequest, Product product) {
        product.setDescription(productRequest.getDescription().toLowerCase());
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setImage(productRequest.getImage());
        product.setIngredients(productRequest.getIngredients());
        product.setAdditional(productRequest.getAdditional());
    }
}