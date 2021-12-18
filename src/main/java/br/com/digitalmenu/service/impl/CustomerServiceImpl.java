package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.repository.CustomerRepository;
import br.com.digitalmenu.service.AddressService;
import br.com.digitalmenu.service.CityService;
import br.com.digitalmenu.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    private final CityService cityService;

    private final AddressService addressService;

    private static final int MIN_SIZE_NAME = 3;


    @Override
    public Customer save(Customer customer) {
        var customerSaved = repository.findByPhone(customer.getPhone());

        return customerSaved.orElseGet(() -> repository.save(validatedCustomer(customer)));
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long clientId) {
        return repository.findById(clientId);
    }

    @Override
    public Customer update(CustomerRequest customerRequest, Customer customer) {
        buildCustomer(customerRequest, customer);
        return repository.save(customer);
    }

    @Override
    public Boolean existsById(Long clientId) {
        return repository.existsById(clientId);
    }

    @Override
    public void delete(Long clientId) {
        Customer customer = repository.findById(clientId).orElseThrow();
        repository.delete(customer);
    }

    @Override
    public Optional<Customer> findByName(String clientName) {
        return repository.findByName(clientName);
    }

    @Override
    public List<Customer> findLikeName(String clientName) {
        return repository.findByNameContaining(clientName);
    }

    private Customer validatedCustomer(final Customer customer) {
        customer.getAddressList().forEach(addressService::validateAddress);
        return customer;
    }

    private void buildCustomer(CustomerRequest customerRequest, Customer customer) {
        nameFormatter(customerRequest.getName());
        customer.setName(nameFormatter(customerRequest.getName()));
        customer.setPhone(customerRequest.getPhone());
        customer.setEmail(customerRequest.getEmail().toLowerCase(Locale.US));
        Set<Address> addressList = new HashSet<>();

        for(var addressRequest : customerRequest.getAddressList()){
            Address address = new Address();
            City city = cityService.findById(addressRequest.getCity().getId())
                    .orElseThrow(() -> new NotFoundException("Entity City is not found"));
            address.setCity(city);
            address.setAddressName(addressRequest.getAddressName());
            address.setNumber(addressRequest.getNumber());
            address.setPostalArea(addressRequest.getPostalArea());
            addressList.add(address);
        }
        customer.getAddressList().addAll(addressList);
    }

    private String nameFormatter(String name){
        name = name.toLowerCase(Locale.US);
        String[] listName = name.split(" ");
        String nameFormatted = "";

        for(var n : listName){
            if(n.length() > MIN_SIZE_NAME){
                n = n.substring(0,1).toUpperCase(Locale.US).concat(n.substring(1));
            }
            nameFormatted = nameFormatted.concat(n).concat(" ");
        }
        return nameFormatted.trim();
    }
}