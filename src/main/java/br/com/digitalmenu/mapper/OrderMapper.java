package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.response.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderFromRequest(Order orderRequest);

    List<OrderResponse> orderToOrderResponse(List<Order> orderList);

    OrderResponse orderToOrderResponse(Order order);
}
