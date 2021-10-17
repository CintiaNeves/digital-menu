package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.CityRequest;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City save(CityRequest cityRequest);
    List<City> findAll();
    Optional<City> findById(Long id);

}
