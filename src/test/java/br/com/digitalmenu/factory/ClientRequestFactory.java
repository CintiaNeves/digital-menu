package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.AddressRequest;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientRequestFactory {
    private final transient CityRepository repository;
    private transient List<AddressRequest> addressList;

    @Autowired
    public ClientRequestFactory(CityRepository repository) {
        this.repository = repository;
    }

    public ClientRequest getDefaultClientRequest() {
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

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setName("Nome Completo do Cliente");
        clientRequest.setEmail("cliente@email.com");
        clientRequest.setPhone("99999999999");
        clientRequest.setAddressList(addressList);
       return clientRequest;
    }

    public ClientRequest getEmpty(){
        return new ClientRequest();
    }
}
