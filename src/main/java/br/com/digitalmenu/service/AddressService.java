package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Address;

import java.util.Optional;

public interface AddressService {
    public Optional<Address> findById(Long id);

}
