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
    public Optional<Customer> findById(Long customerId) {
        return repository.findById(customerId);
    }

    @Override
    public Customer update(Long customerId, CustomerRequest customerRequest) {
        var customerSaved = findById(customerId)
                .orElseThrow(()-> new NotFoundException(String.format("Customer with id %s not found.", customerId)));
        updateCustomer(customerRequest, customerSaved);
        return repository.save(customerSaved);
    }

    @Override
    public void delete(Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(()-> new NotFoundException(String.format("Customer with id %s not found.", customerId)));
        repository.delete(customer);
    }

    @Override
    public List<Customer> findLikeName(String customerId) {
        return repository.findByNameContaining(customerId);
    }

    private Customer validatedCustomer(final Customer customer) {
        customer.getAddressList().forEach(addressService::validateAddress);
        return customer;
    }

    private void updateCustomer(CustomerRequest customerRequest, Customer customer) {
        customer.setName(nameFormatter(customerRequest.getName()));
        customer.setPhone(customerRequest.getPhone());
        customer.setEmail(customerRequest.getEmail().toLowerCase(Locale.US));
        Set<Address> addressList = new HashSet<>();

        customerRequest.getAddressList().forEach(addressRequest -> {
            City city = cityService.findByName(addressRequest.getCity().getName())
                    .orElseThrow(() -> new NotFoundException("Entity City is not found."));

            addressList.add(Address.builder()
                    .city(city)
                    .addressName(addressRequest.getAddressName())
                    .number(addressRequest.getNumber())
                    .postalArea(addressRequest.getPostalArea())
                    .build());
        });
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