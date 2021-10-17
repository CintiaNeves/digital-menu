package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(ProductRequest productRequest);

    List<Product> findAll();

    Optional<Product> findById(Long productId);

    Product update(Long productId, ProductRequest productRequest);

    Boolean existsById(Long productId);

    void delete(Long productId);

    Optional<Product> findByDescription(String productDescription);

    List<Product> findLikeDescription(String productDescription);

    Product uploadImage(MultipartFile image, Long productId);
}
