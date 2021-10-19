package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.domain.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order save(OrderRequest orderRequest);

    List<Order> findAll();

    List<Order> findByClientId(Long clientId);

    Boolean existsById(Long orderId);

    Order update(Long orderId, Status status);
}
