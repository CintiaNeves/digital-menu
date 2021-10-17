package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.repository.AddressRepository;
import br.com.digitalmenu.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public Optional<Address> findById(Long id) {
        return repository.findById(id);
    }
}