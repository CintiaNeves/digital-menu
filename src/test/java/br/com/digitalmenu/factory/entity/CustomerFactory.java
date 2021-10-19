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
        var customer = new Customer();
        customer.setName("Nome Completo do Cliente");
        customer.setEmail("cliente@email.com");
        customer.setPhone("99999999999");
        customer.getAddressList().addAll(List.of(addressFactory.getDefaultAddress()));

        return customer;
    }

    public Customer getPersistedCustomer() {
        return customerRepository.save(this.getDefaultCustomer());
    }

}