package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.entity.OrderItem;
import br.com.digitalmenu.domain.entity.Orders;
import br.com.digitalmenu.domain.enums.Status;
import br.com.digitalmenu.repository.ClientRepository;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderFactory {

    private final transient ClientRepository clientRepository;
    private final transient ProductRepository productRepository;
    private final transient ClientFactory clientFactory;
    private final transient ProductFactory productFactory;
    private transient List<OrderItem> orderItemList;

    public OrderFactory(ClientRepository clientRepository,
                        ProductRepository productRepository,
                        ClientFactory clientFactory,
                        ProductFactory productFactory) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.clientFactory = clientFactory;
        this.productFactory = productFactory;
    }

    public Orders getDefaultOrder() {
        OrderItem item = new OrderItem();
        Client client = clientRepository.save(clientFactory.getDefaultClient());
        item.setProduct(productRepository.save(productFactory.getDefaultFoodProduct()));
        item.setPriceItem(15D);
        item.setAmount(1);
        Orders order = new Orders();
        order.setStatus(Status.OPEN);
        order.setAddress(client.getAddressList().get(0));
        order.setClient(client);
        return order;
    }
}