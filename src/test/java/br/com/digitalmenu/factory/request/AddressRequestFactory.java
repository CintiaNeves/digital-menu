package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.AddressRequest;
import br.com.digitalmenu.factory.entity.CityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestFactory {

    private final CityFactory cityFactory;

    @Autowired
    public AddressRequestFactory(CityFactory cityFactory) {
        this.cityFactory = cityFactory;
    }

    public AddressRequest getDefaultAddressRequest() {
        return AddressRequest.builder()
            .postalArea("08700000")
            .number(10)
            .addressName("Avenida Paulista")
            .city(cityFactory.getPersistedCity())
            .build();
    }
}
