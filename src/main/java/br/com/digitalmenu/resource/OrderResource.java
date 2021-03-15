package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.domain.request.ProductRequest;
import br.com.digitalmenu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orders create (@Valid @RequestBody OrderRequest orderRequest){
        return service.save(orderRequest);
    }

    @GetMapping
    public List<Orders> findAll (){
        return service.findAll();
    }

    @GetMapping("/{clientId}")
    public List<Orders> findByClientId (@PathVariable Long clientId){
        return service.findByClientId(clientId);
    }
}
