package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Order;
import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.exception.CalculationPriceItemException;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.repository.OrderRepository;
import br.com.digitalmenu.service.AddressService;
import br.com.digitalmenu.service.CustomerService;
import br.com.digitalmenu.service.OrderService;
import br.com.digitalmenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final CustomerService customerService;

    private final ProductService productService;

    private final AddressService addressService;

    @Override
    public Order save(OrderRequest orderRequest) {
        Order order = new Order();
        buildOrder(orderRequest, order);
        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Order> findByClientId(Long clientId) {
        return repository.findByCustomerId(clientId);
    }

    @Override
    public Boolean existsById(Long orderId) {
        return repository.existsById(orderId);
    }

    @Override
    public Order update(Long orderId, Status status) {
        Order order = repository.findById(orderId).get();
        order.setStatus(status);
        return repository.save(order);
    }

    private void buildOrder(OrderRequest orderRequest, Order order) {
        order.setOrderItemList(new ArrayList<>());
        for(var itemRequest : orderRequest.getOrderItemList()){
            Product product = productService.findById(itemRequest.getProductId())
                    .orElseThrow(supplier("Entity Product is not found"));

            OrderItem item = createOrderItem(itemRequest, product);

            if(!priceIsValid(product, item)){
                throw new CalculationPriceItemException("Price item is divergent from price product");
            }
           order.getOrderItemList().add(item);
        }
        order.setStatus(Status.OPEN);
        order.setCustomer(customerService.findById(orderRequest.getClientId())
                .orElseThrow(supplier("Entity Costumer is not found")));
        order.setAddress(addressService.findById(orderRequest.getAddressId())
                .orElseThrow(supplier("Entity Address is not found")));
    }

    private Supplier<NotFoundException> supplier(String message) {
        return () -> new NotFoundException(message);
    }

    private OrderItem createOrderItem(OrderItemRequest itemRequest, Product product) {
        var orderItem = new OrderItem();
        orderItem.setAmount(itemRequest.getAmount());
        orderItem.setProduct(product);
        orderItem.setPriceItem(itemRequest.getPriceItem());
        return orderItem;
    }

    private boolean priceIsValid(Product product, OrderItem item){
        return (product.getPrice().equals(item.getPriceItem()));
    }
}