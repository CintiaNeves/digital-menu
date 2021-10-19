package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;

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
        var order = new Order();
        order.setStatus(Status.OPEN);
        order.setAddress(customer.getAddressList().get(0));
        order.setCustomer(customer);
        order.setOrderItemList(List.of(orderItemFactory.getDefaultOrderItem()));
        return order;
    }
}