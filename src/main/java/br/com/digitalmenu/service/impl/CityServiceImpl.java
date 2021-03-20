package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.CityRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.repository.CityRepository;
import br.com.digitalmenu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private transient CityRepository repository;

    @Override
    public City save(CityRequest cityRequest) {
        if (repository.findByName(cityRequest.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Entity already exists.");
        }
        City city = new City();
        city.setName(cityRequest.getName());
        return repository.save(city);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return repository.findById(id);
    }
}