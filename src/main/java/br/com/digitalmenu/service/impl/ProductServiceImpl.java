package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.exception.UploadProductImageException;
import br.com.digitalmenu.repository.ProductRepository;
import br.com.digitalmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Override
    public Product uploadImage(MultipartFile image, Long productId) {
        try {
            Product product = repository.findById(productId).get();

            URL url = getClass().getClassLoader().getResource("static/images/" + product.getDescription());
            Path directoryPath = Path.of(url.toURI());

            deleteFilesInDirectory(directoryPath);

            Path imagePath = directoryPath.resolve(image.getOriginalFilename());
            Files.createDirectories(directoryPath);
            image.transferTo(imagePath.toFile());
            product.setImagePath(imagePath.toString());
            repository.save(product);
            return product;
        } catch (IOException | URISyntaxException e) {
            throw new UploadProductImageException("Failed to save image.");
        }
    }

    private void deleteFilesInDirectory(Path directoryPath) {
        directoryPath.toFile().mkdirs();
        if(Files.isDirectory(directoryPath)) {
            for(var file: directoryPath.toFile().listFiles()) {
                if(!Files.isDirectory(file.toPath())) {
                   file.delete();
                }
            }
        }
    }

    private void buildProduct(ProductRequest productRequest, Product product) {
        product.setDescription(productRequest.getDescription().toLowerCase());
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setIngredients(productRequest.getIngredients());
        product.setAdditional(productRequest.getAdditional());
    }

}