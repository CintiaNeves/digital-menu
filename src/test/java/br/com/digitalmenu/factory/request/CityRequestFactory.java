package br.com.digitalmenu.factory.request;

import br.com.digitalmenu.domain.request.CityRequest;
import org.springframework.stereotype.Component;

@Component
public class CityRequestFactory {

    public CityRequest getDefaultCityRequest() {
        var cityRequest = new CityRequest();
        cityRequest.setName("Mogi das Cruzes");
        return cityRequest;
    }
}
