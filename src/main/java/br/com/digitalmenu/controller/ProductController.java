package br.com.digitalmenu.controller;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.domain.response.ProductResponse;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.mapper.ProductMapper;
import br.com.digitalmenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> create (@Valid @RequestBody ProductRequest productRequest){
        var product = service.save(productMapper.toProduct(productRequest));

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{productId}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(productMapper.toProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll (){
        return ResponseEntity.ok(productMapper.toProductResponseList(service.findAll()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById (@PathVariable Long productId){
        var product = service.findById(productId)
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found.", productId)));

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update (@PathVariable Long productId, @Valid @RequestBody ProductRequest productRequest){
        var product = service.findById(productId)
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found.", productId)));

        return ResponseEntity.ok(productMapper.toProductResponse(service.update(productRequest, product)));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete (@Valid @PathVariable Long productId){
        var product = service.findById(productId)
                .orElseThrow(()-> new NotFoundException(String.format("Product with id %s not found.", productId)));

        service.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{productDescription}")
    public ResponseEntity<List<ProductResponse>> findLikeDescription (@PathVariable String productDescription){
        return ResponseEntity.ok(productMapper.toProductResponseList(service.findLikeDescription(productDescription)));
    }

    @PutMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile image, @RequestParam Long productId){
        if(!service.existsById(productId)){
            return ResponseEntity.notFound().build();
        }
        try{
            return ResponseEntity.ok(service.uploadImage(image, productId));
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.toString());
        }
    }
}