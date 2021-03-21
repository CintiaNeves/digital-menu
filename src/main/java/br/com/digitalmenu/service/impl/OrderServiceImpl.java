package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.exception.CalculationPriceItemException;
import br.com.digitalmenu.exception.EntityNotFoundException;
import br.com.digitalmenu.repository.OrderRepository;
import br.com.digitalmenu.service.AddressService;
import br.com.digitalmenu.service.ClientService;
import br.com.digitalmenu.service.OrderService;
import br.com.digitalmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private transient OrderRepository repository;

    @Autowired
    private transient ClientService clientService;

    @Autowired
    private transient ProductService productService;

    @Autowired
    private transient AddressService addressService;

    @Override
    public Orders save(OrderRequest orderRequest) {
        Orders orders = new Orders();
        buildOrder(orderRequest, orders);
        return repository.save(orders);
    }

    @Override
    public List<Orders> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Orders> findByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    @Override
    public Boolean existsById(Long orderId) {
        return repository.existsById(orderId);
    }

    @Override
    public Orders update(Long orderId, Status status) {
        Orders order = repository.findById(orderId).get();
        order.setStatus(status);
        return repository.save(order);
    }

    private void buildOrder(OrderRequest orderRequest, Orders orders) {
        for(var itemRequest : orderRequest.getOrderItemList()){
            Product product = productService.findById(itemRequest.getProductId())
                    .orElseThrow(supplier("Entity Product is not found"));

            OrderItem item = createOrderItem(itemRequest, product);

            if(!priceIsValid(product, item)){
                throw new CalculationPriceItemException("Price item is divergent from price product");
            }
           orders.getOrderItemList().add(item);
        }
        orders.setStatus(Status.OPEN);
        orders.setClient(clientService.findById(orderRequest.getClientId())
                .orElseThrow(supplier("Entity Client is not found")));
        orders.setAddress(addressService.findById(orderRequest.getAddressId())
                .orElseThrow(supplier("Entity Address is not found")));
    }

    private Supplier<EntityNotFoundException> supplier(String message) {
        return () -> new EntityNotFoundException(message);
    }

    private OrderItem createOrderItem(OrderItemRequest itemRequest, Product product) {
        return new OrderItem(itemRequest.getAmount(), itemRequest.getPriceItem(), product);
    }

    private boolean priceIsValid(Product product, OrderItem item){
        return (product.getPrice().equals(item.getPriceItem()));
    }
}