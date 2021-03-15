package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.entity.Client;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;

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

    private void buildOrder(OrderRequest orderRequest, Orders orders) {

        Set<OrderItem> orderItemList = new HashSet<>();
        Client client = clientService.findById(orderRequest.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Client is not found"));
        Address address = addressService.findById(orderRequest.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Entity Address is not found"));

        for(var itemRequest : orderRequest.getOrderItemList()){
            OrderItem item = new OrderItem();
            Product product = productService.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Entity Product is not found"));
            item.setAmount(itemRequest.getAmount());
            item.setProduct(product);
            item.setPriceItem(itemRequest.getPriceItem());
            if(!priceIsValid(product, item)){
                throw new CalculationPriceItemException("Price item is divergent from price product");
            }
            orderItemList.add(item);
        }
        orders.setOrderItemList(orderItemList);
        orders.setClient(client);
        orders.setStatus(Status.OPEN);
        orders.setAddress(address);
    }

    private boolean priceIsValid(Product product, OrderItem item){
        return (product.getPrice().equals(item.getPriceItem()));
    }
}
