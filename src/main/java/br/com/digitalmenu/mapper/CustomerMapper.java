package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.Customer;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.domain.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datCreate", ignore = true)
    @Mapping(target = "datUpdate", ignore = true)
    Customer customerFromRequest(CustomerRequest customerRequest);

    List<CustomerResponse> customerToCustomerResponse(List<Customer> customerList);

    CustomerResponse customerToCustomerResponse(Customer customer);
}
