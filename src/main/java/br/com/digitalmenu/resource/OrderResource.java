package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.request.OrderRequest;
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

@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create (@Valid @RequestBody OrderRequest orderRequest){
        try{
            Orders orders = service.save(orderRequest);
            return ResponseEntity.ok(orders);
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.toString());
        }
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
