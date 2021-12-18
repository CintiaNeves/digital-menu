package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.repository.AddressRepository;
import br.com.digitalmenu.service.AddressService;
import br.com.digitalmenu.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    private final CityService cityService;

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Address validateAddress(Address address) {
        var city = cityService.findById(address.getCity().getId())
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found.", address.getCity().getId())));
        address.setCity(city);
        return address;
    }
}