package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {

    private final AddressRepository addressRepository;

    private final CityFactory cityFactory;

    @Autowired
    public AddressFactory(AddressRepository addressRepository, CityFactory cityFactory) {
        this.addressRepository = addressRepository;
        this.cityFactory = cityFactory;
    }

    public Address getDefaultAddress() {
        return Address.builder()
            .postalArea("08700000")
            .number(10)
            .addressName("Avenida Paulista")
            .city(cityFactory.getPersistedCity())
            .build();
    }

    public Address getPersistedAddress() {
        return addressRepository.save(this.getDefaultAddress());
    }
}
