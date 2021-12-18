package br.com.digitalmenu.controller;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.domain.response.CustomerResponse;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.mapper.CustomerMapper;
import br.com.digitalmenu.service.CustomerService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> create (@Valid @RequestBody CustomerRequest customerRequest){
        var customer = service.save(customerMapper.customerFromRequest(customerRequest));

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{customerId}")
                .buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(uri).body(customerMapper.customerToCustomerResponse(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll (){
        return ResponseEntity.ok(customerMapper.customerToCustomerResponse(service.findAll()));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById (@PathVariable Long customerId){
        var customer = service.findById(customerId)
                .orElseThrow(()-> new NotFoundException(String.format("Customer with id %s not found.", customerId)));

        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> update (@PathVariable Long customerId, @Valid @RequestBody CustomerRequest customerRequest){
        var customer = service.findById(customerId)
                .orElseThrow(()-> new NotFoundException(String.format("Customer with id %s not found.", customerId)));

        return ResponseEntity.ok(customerMapper.customerToCustomerResponse(service.update(customerRequest,
                customerMapper.customerFromRequest(customerRequest))));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete (@Valid @PathVariable Long customerId){
        var customer = service.findById(customerId)
                .orElseThrow(()-> new NotFoundException(String.format("Customer with id %s not found.", customerId)));

        service.delete(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{customerName}")
    public ResponseEntity<List<CustomerResponse>> findLikeName (@PathVariable String customerName){
        return ResponseEntity.ok(customerMapper.customerToCustomerResponse(service.findLikeName(customerName)));
    }
}