package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.OrderStatusRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusRequestFactory {

    public OrderStatusRequest getEmpty() {
        return new OrderStatusRequest();
    }
}