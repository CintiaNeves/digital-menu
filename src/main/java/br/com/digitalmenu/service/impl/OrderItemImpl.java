package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemImpl implements OrderItemService {

    private ProductServiceImpl productService;

    @Override
    public OrderItem toOrderItem(final OrderItemRequest orderItemRequest) {
        log.info("M=validateRequest, I=Validando dados do item da ordem, orderItemRequest={}", orderItemRequest);

        var product = productService.findById(orderItemRequest.getProductId())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found.", orderItemRequest.getProductId())));

        return OrderItem.builder()
                .amount(orderItemRequest.getAmount())
                .product(product)
                .build();

    }
}
