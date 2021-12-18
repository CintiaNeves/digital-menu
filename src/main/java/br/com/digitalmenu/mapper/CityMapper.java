package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.CityRequest;
import br.com.digitalmenu.domain.response.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datCreate", ignore = true)
    @Mapping(target = "datUpdate", ignore = true)
    City cityFromRequest(CityRequest cityRequest);

    CityResponse cityToCityResponse(City city);

    List<CityResponse> cityToCityResponse(List<City> cityList);
}
