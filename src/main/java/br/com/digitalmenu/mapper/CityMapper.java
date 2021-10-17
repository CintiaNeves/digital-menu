package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.CityRequest;
import br.com.digitalmenu.domain.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final ModelMapper modelMapper;

    public City toCity(CityRequest cityRequest) {
        return modelMapper.map(cityRequest, City.class);
    }

    public CityResponse toCityResponse(City city) {
        return modelMapper.map(city, CityResponse.class);
    }

    public List<CityResponse> toCityResponseList(List<City> cities){
        return cities.stream()
                .map(city -> toCityResponse(city))
                .collect(Collectors.toList());
    }
}
