package br.com.digitalmenu.controller;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerResource {

    private final CustomerService service;

    @GetMapping
    public List<Customer> findAll (){
        return service.findAll();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById (@PathVariable Long customerId){
        Optional<Customer> customer = service.findById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create (@Valid @RequestBody CustomerRequest customerRequest){
        try{
            Customer customer = service.save(customerRequest);
            return ResponseEntity.ok(customer);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.toString());
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> update (@PathVariable Long customerId, @Valid @RequestBody CustomerRequest customerRequest){

        if(!service.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(customerId, customerRequest));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete (@Valid @PathVariable Long customerId){

        if(!service.existsById(customerId)){
            return ResponseEntity.notFound().build();
        }
        service.delete(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{customerName}")
    public List<Customer> findLikeName (@PathVariable String customerName){
        return service.findLikeName(customerName);
    }
}