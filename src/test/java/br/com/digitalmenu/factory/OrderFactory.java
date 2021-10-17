package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.repository.CustomerRepository;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderFactory {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CustomerFactory customerFactory;
    private final ProductFactory productFactory;
    private List<OrderItem> orderItemList;

    public OrderFactory(CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        CustomerFactory customerFactory,
                        ProductFactory productFactory) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerFactory = customerFactory;
        this.productFactory = productFactory;
    }

    public Orders getDefaultOrder() {
        OrderItem item = new OrderItem();
        Customer customer = customerRepository.save(customerFactory.getDefaultClient());
        item.setProduct(productRepository.save(productFactory.getDefaultFoodProduct()));
        item.setPriceItem(15D);
        item.setAmount(1);
        Orders order = new Orders();
        order.setStatus(Status.OPEN);
        order.setAddress(customer.getAddressList().get(0));
        order.setCustomer(customer);
        return order;
    }
}