package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(Long customerId);

    Customer update(Long customerId, CustomerRequest customerRequest);

    void delete(Long customerId);

    List<Customer> findLikeName(String customerId);
}
