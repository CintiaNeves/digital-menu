package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.request.OrderItemRequest;

public interface OrderItemService {

    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

}
