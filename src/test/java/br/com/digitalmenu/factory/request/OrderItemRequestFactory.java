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
        return OrderItemRequest.builder()
            .amount(5)
            .productId(productFactory.getPersistedProduct().getId())
            .build();
    }
}
