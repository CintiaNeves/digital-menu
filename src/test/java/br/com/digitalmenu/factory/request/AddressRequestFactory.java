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
        var addressRequest = new AddressRequest();
        addressRequest.setPostalArea("08700000");
        addressRequest.setNumber(10);
        addressRequest.setAddressName("Avenida Paulista");
        addressRequest.setCityId(cityFactory.getPersistedCity().getId());
        return addressRequest;
    }
}
