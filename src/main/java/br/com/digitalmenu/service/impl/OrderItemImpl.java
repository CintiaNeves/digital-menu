package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.repository.OrderItemRepository;
import br.com.digitalmenu.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        if(isValidItem(orderItem)) {
            return orderItemRepository.save(orderItem);
        }
        return orderItem;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAll(List<OrderItem> items) {
        items.forEach(this::save);
    }

    private boolean isValidItem(OrderItem orderItem) {
        return true;
    }
}
