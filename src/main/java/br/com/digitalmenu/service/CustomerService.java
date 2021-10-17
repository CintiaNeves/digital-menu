package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(Long clientId);

    Customer update(CustomerRequest customerRequest, Customer customer);

    Boolean existsById(Long clientId);

    void delete(Long clientId);

    Optional<Customer> findByName(String clientName);

    List<Customer> findLikeName(String clientName);
}
