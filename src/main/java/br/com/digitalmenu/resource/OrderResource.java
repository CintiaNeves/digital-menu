package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.domain.request.OrderStatusRequest;
import br.com.digitalmenu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderResource {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody OrderRequest orderRequest) {
        try {
            Orders orders = service.save(orderRequest);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.toString());
        }
    }

    @GetMapping
    public List<Orders> findAll() {
        return service.findAll();
    }

    @GetMapping("/{clientId}")
    public List<Orders> findByClientId(@PathVariable Long clientId) {
        return service.findByClientId(clientId);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> update(@PathVariable Long orderId, @Valid @RequestBody OrderStatusRequest orderStatusRequest) {

        if (!service.existsById(orderId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(orderId, orderStatusRequest.getStatus()));
    }
}