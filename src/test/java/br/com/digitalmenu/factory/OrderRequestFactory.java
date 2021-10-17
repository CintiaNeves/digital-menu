package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.repository.CustomerRepository;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderRequestFactory {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CustomerFactory customerFactory;
    private final ProductFactory productFactory;
    private List<OrderItemRequest> itemRequestList;

    @Autowired
    public OrderRequestFactory(CustomerRepository customerRepository,
                               CustomerFactory customerFactory,
                               ProductRepository productRepository,
                               ProductFactory productFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    public OrderRequest getDefaultOrderRequest() {
        Customer customer = customerRepository.save(customerFactory.getDefaultClient());
        Product product = productRepository.save(productFactory.getDefaultFoodProduct());
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setClientId(customer.getId());
        orderRequest.setAddressId(customer.getAddressList().get(0).getId());
        itemRequestList = new ArrayList<>();
        OrderItemRequest item = new OrderItemRequest();
        item.setAmount(5);
        item.setPriceItem(15D);
        item.setProductId(product.getId());
        itemRequestList.add(item);
        orderRequest.setOrderItemList(itemRequestList);
        return orderRequest;
    }
}
