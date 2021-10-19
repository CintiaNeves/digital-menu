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
        var address = new Address();
        address.setPostalArea("08700000");
        address.setNumber(10);
        address.setAddressName("Avenida Paulista");
        address.setCity(cityFactory.getPersistedCity());
        return address;
    }

    public Address getPersistedAddress() {
        return addressRepository.save(this.getDefaultAddress());
    }
}
