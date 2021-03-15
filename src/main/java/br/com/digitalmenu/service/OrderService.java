package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Orders save(OrderRequest orderRequest);

    public List<Orders> findAll();

    List<Orders> findByClientId(Long clientId);
}
