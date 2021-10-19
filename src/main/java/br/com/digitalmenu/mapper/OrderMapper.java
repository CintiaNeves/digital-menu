package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.domain.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public Order toOrder(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, Order.class);
    }

    public OrderResponse toOrderResponse(Order order) {
        return modelMapper.map(order, OrderResponse.class);
    }

    public List<OrderResponse> toOrderResponseList(List<Order> orders){
        return orders.stream()
                .map(this::toOrderResponse)
                .collect(Collectors.toList());
    }
}
