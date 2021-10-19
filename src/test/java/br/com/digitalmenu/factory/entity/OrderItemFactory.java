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
        var orderItem = new OrderItem();
        orderItem.setPriceItem(15D);
        orderItem.setAmount(1);
        orderItem.setProduct(productFactory.getPersistedProduct());
        return  orderItem;
    }
}
