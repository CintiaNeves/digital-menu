package br.com.digitalmenu.mapper;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.request.AddressRequest;
import br.com.digitalmenu.domain.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "datCreate", ignore = true)
    @Mapping(target = "datUpdate", ignore = true)
    Address addressFromRequest(AddressRequest addressRequest);

    AddressResponse addressToAddressResponse(Address address);
}
