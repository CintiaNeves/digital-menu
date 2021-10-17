package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.AddressRequest;
import br.com.digitalmenu.domain.request.CustomerRequest;
import br.com.digitalmenu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomerRequestFactory {
    private final CityRepository repository;
    private List<AddressRequest> addressList;

    @Autowired
    public CustomerRequestFactory(CityRepository repository) {
        this.repository = repository;
    }

    public CustomerRequest getDefaultClientRequest() {
        City city = new City();
        city.setName("São Paulo");
        city = repository.save(city);
        addressList = new ArrayList<>();
        AddressRequest address = new AddressRequest();
        address.setPostalArea("08700000");
        address.setNumber(10);
        address.setAddressName("Avenida Paulista");
        address.setCityId(city.getId());
        addressList.add(address);

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("Nome Completo do Cliente");
        customerRequest.setEmail("cliente@email.com");
        customerRequest.setPhone("99999999999");
        customerRequest.setAddressList(addressList);
       return customerRequest;
    }

    public CustomerRequest getEmpty(){
        return new CustomerRequest();
    }
}
