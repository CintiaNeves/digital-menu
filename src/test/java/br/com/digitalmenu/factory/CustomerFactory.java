package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.repository.CityRepository;
import br.com.digitalmenu.repository.CustomerRepository;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class CustomerFactory {

    private final CityRepository cityRepository;

    private final CustomerRepository customerRepository;

    private Set<Address> addressList;

    public CustomerFactory(CityRepository cityRepository, CustomerRepository customerRepository) {
        this.cityRepository = cityRepository;
        this.customerRepository = customerRepository;
    }

    public Customer getDefaultCustomer() {
        City city = new City();
        city.setName("São Paulo");
        city = cityRepository.save(city);

        addressList = new HashSet<>();
        Address address = new Address();
        address.setPostalArea("08700000");
        address.setNumber(10);
        address.setAddressName("Avenida Paulista");
        address.setCity(city);
        addressList.add(address);

        Customer customer = new Customer();
        customer.setName("Nome Completo do Cliente");
        customer.setEmail("cliente@email.com");
        customer.setPhone("99999999999");
        customer.getAddressList().addAll(addressList);

        return customer;
    }

    public Customer getPersistedCustomer() {
        return customerRepository.save(this.getDefaultCustomer());
    }

    public Customer getEmpty(){
        return new Customer();
    }
}