package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemFactory {

    private final ProductFactory productFactory;
    @Autowired

    public OrderItemFactory(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    public OrderItem getDefaultOrderItem() {
        return OrderItem.builder()
            .amount(1)
            .product(productFactory.getPersistedProduct())
            .build();
    }
}
