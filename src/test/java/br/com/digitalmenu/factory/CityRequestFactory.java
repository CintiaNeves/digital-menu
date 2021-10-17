package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.request.CityRequest;
import org.springframework.stereotype.Component;

@Component
public class CityRequestFactory {

    public CityRequest getDefaultCityRequest() {
        CityRequest cityRequest = new CityRequest();
        cityRequest.setName("Mogi das Cruzes");
        return cityRequest;
    }
}
