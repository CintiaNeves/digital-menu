package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City save(City city);

    List<City> findAll();

    Optional<City> findById(Long id);
}
