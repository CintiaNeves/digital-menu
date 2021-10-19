package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.factory.entity.ProductFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemRequestFactory {

    private final ProductFactory productFactory;

    @Autowired
    public OrderItemRequestFactory(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    public OrderItemRequest getDefaultOrderItemRequest() {
        var item = new OrderItemRequest();
        item.setAmount(5);
        item.setPriceItem(15D);
        item.setProductId(productFactory.getPersistedProduct().getId());
        return item;
    }
}
