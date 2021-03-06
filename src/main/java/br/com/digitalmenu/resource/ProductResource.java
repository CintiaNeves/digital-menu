package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private transient ProductService service;

    @GetMapping
    public List<Product> findAll (){
        return service.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById (@PathVariable Long productId){
        Optional<Product> product = service.findById(productId);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Product create (@Valid @RequestBody ProductRequest productRequest){
        return service.save(productRequest);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update (@PathVariable Long productId, @Valid @RequestBody ProductRequest productRequest){

        if(!service.existsById(productId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(productId, productRequest));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete (@Valid @PathVariable Long productId){

        if(!service.existsById(productId)){
            return ResponseEntity.notFound().build();
        }
        service.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{productDescription}")
    public List<Product> findLikeDescription (@PathVariable String productDescription){
        return service.findLikeDescription(productDescription);
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