package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    void saveAll(List<OrderItem> items);

}
