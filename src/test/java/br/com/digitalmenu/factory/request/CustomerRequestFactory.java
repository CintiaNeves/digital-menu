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

    public CustomerRequest getDefaultCustomerRequest() {
        return CustomerRequest.builder()
            .name("Nome Completo do Cliente")
            .email("cliente@email.com")
            .phone("99999999999")
            .addressList(List.of(addressRequestFactory.getDefaultAddressRequest()))
            .build();
    }
}
