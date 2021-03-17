package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product save(ProductRequest productRequest);

    public List<Product> findAll();

    public Optional<Product> findById(Long productId);

    public Product update (Long productId, ProductRequest productRequest);

    public Boolean existsById(Long productId);

    public void delete(Long productId);

    public Optional<Product> findByDescription(String productDescription);

    public List<Product> findLikeDescription(String productDescription);

    public Product uploadImage(MultipartFile image, Long productId);
}
