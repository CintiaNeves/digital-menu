package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.request.OrderStatusRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderStatusRequestFactory {

    public OrderStatusRequest getEmpty() {
        return new OrderStatusRequest();
    }
}