package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.exception.UploadProductImageException;
import br.com.digitalmenu.repository.ProductRepository;
import br.com.digitalmenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
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
    public Product update(ProductRequest productRequest, Product product) {
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
    @Transactional
    public Product uploadImage(MultipartFile image, Long productId) {
        Product product = repository.findById(productId).get();
        Path directoryPath = Paths.get(new File("").getAbsolutePath().concat("/src/main/resources/static/images"), product.getDescription());
        Path imagePath = directoryPath.resolve(Objects.requireNonNull(image.getOriginalFilename()));

        try {
            cleanDirectory(directoryPath);
            Files.createDirectories(directoryPath);
            image.transferTo(imagePath.toFile());
            product.setImagePath(imagePath.toString());
            repository.save(product);
            return product;
        } catch (IOException e) {
            throw new UploadProductImageException("Failed to save image.");
        }
    }

    private void cleanDirectory(Path directoryPath) throws IOException {
        if(Files.exists(directoryPath)){
            FileUtils.cleanDirectory(directoryPath.toFile());
        }
    }

    private void buildProduct(ProductRequest productRequest, Product product) {
        product.setDescription(productRequest.getDescription().toLowerCase(Locale.US));
        product.setPrice(productRequest.getPrice());
        product.setCategory(productRequest.getCategory());
        product.setIngredients(productRequest.getIngredients());
        product.setAdditional(productRequest.getAdditional());
    }
}