package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.repository.CityRepository;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ClientFactory {
    private final transient CityRepository repository;
    private transient Set<Address> addressList;

    public ClientFactory(CityRepository repository) {
        this.repository = repository;
    }

    public Client getDefaultClient() {
        City city = new City();
        city.setName("São Paulo");
        city = repository.save(city);

        addressList = new HashSet<>();
        Address address = new Address();
        address.setPostalArea("08700000");
        address.setNumber(10);
        address.setAddressName("Avenida Paulista");
        address.setCity(city);
        addressList.add(address);

        Client client = new Client();
        client.setName("Nome Completo do Cliente");
        client.setEmail("cliente@email.com");
        client.setPhone("99999999999");
        client.getAddressList().addAll(addressList);

        return client;
    }
}