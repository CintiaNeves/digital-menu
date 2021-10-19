package br.com.digitalmenu.controller;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.mapper.OrderMapper;
import br.com.digitalmenu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Order> create (@Valid @RequestBody OrderRequest orderRequest){
        var order = service.save(orderRequest);

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{productId}")
                .buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(order);
    }
}