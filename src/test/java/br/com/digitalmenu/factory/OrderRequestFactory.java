package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.entity.Product;
import br.com.digitalmenu.domain.request.OrderItemRequest;
import br.com.digitalmenu.domain.request.OrderRequest;
import br.com.digitalmenu.repository.ClientRepository;
import br.com.digitalmenu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderRequestFactory {
    private final transient ClientRepository clientRepository;
    private final transient ProductRepository productRepository;
    private final transient ClientFactory clientFactory;
    private final transient ProductFactory productFactory;
    private transient List<OrderItemRequest> itemRequestList;

    @Autowired
    public OrderRequestFactory(ClientRepository clientRepository,
                               ClientFactory clientFactory,
                               ProductRepository productRepository,
                               ProductFactory productFactory) {
        this.clientRepository = clientRepository;
        this.clientFactory = clientFactory;
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    public OrderRequest getDefaultOrderRequest() {
        Client client = clientRepository.save(clientFactory.getDefaultClient());
        Product product = productRepository.save(productFactory.getDefaultFoodProduct());
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setClientId(client.getId());
        orderRequest.setAddressId(client.getAddressList().get(0).getId());
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
