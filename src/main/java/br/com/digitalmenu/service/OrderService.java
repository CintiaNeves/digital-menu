package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.domain.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Orders save(OrderRequest orderRequest);

    List<Orders> findAll();

    List<Orders> findByClientId(Long clientId);

    Boolean existsById(Long orderId);

    Orders update(Long orderId, Status status);
}
