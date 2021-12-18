package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFactory {

    private final AddressFactory addressFactory;

    private final CustomerRepository customerRepository;

    public CustomerFactory(AddressFactory addressFactory, CustomerRepository customerRepository) {
        this.addressFactory = addressFactory;
        this.customerRepository = customerRepository;
    }

    public Customer getDefaultCustomer() {
        return Customer.builder()
            .name("Nome Completo do Cliente")
            .email("cliente@email.com")
            .phone("99999999999")
            .addressList(List.of(addressFactory.getDefaultAddress()))
            .build();
    }

    public Customer getPersistedCustomer() {
        return customerRepository.save(this.getDefaultCustomer());
    }

}