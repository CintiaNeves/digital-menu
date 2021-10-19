package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRequestFactory {

    private final AddressRequestFactory addressRequestFactory;

    @Autowired
    public CustomerRequestFactory(AddressRequestFactory addressRequestFactory) {
        this.addressRequestFactory = addressRequestFactory;
    }

    public CustomerRequest getDefaultClientRequest() {
        var customerRequest = new CustomerRequest();
        customerRequest.setName("Nome Completo do Cliente");
        customerRequest.setEmail("cliente@email.com");
        customerRequest.setPhone("99999999999");
        customerRequest.setAddressList(List.of(addressRequestFactory.getDefaultAddressRequest()));
       return customerRequest;
    }
}
