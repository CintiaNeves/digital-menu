package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.digitalmenu.domain.enums.Status.OPEN;

@Component
public class OrderFactory {

    private final CustomerFactory customerFactory;

    private final OrderItemFactory orderItemFactory;

    public OrderFactory(CustomerFactory customerFactory,
                        OrderItemFactory orderItemFactory) {
        this.customerFactory = customerFactory;
        this.orderItemFactory = orderItemFactory;
    }

    public Order getDefaultOrder() {
        var customer = customerFactory.getPersistedCustomer();
        return Order.builder()
            .status(OPEN)
            .address(customer.getAddressList().get(0))
            .customer(customer)
            .orderItemList(List.of(orderItemFactory.getDefaultOrderItem()))
            .build();

    }
}