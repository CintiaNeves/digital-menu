package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.factory.entity.CustomerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRequestFactory {

    private final CustomerFactory customerFactory;

    private final OrderItemRequestFactory orderItemRequestFactory;

    @Autowired
    public OrderRequestFactory(CustomerFactory customerFactory,
                               OrderItemRequestFactory orderItemRequestFactory) {
        this.customerFactory = customerFactory;
        this.orderItemRequestFactory = orderItemRequestFactory;
    }

    public OrderRequest getDefaultOrderRequest() {
        var customer = customerFactory.getPersistedCustomer();
        return OrderRequest.builder()
            .customerId(customer.getId())
            .addressId(customer.getAddressList().get(0).getId())
            .orderItemList(List.of(orderItemRequestFactory.getDefaultOrderItemRequest()))
            .build();
    }
}
