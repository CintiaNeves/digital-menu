package br.com.digitalmenu.factory.entity;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityFactory {

    @Autowired
    private CityRepository repository;

    public City getDefaultCity() {
        return City.builder()
            .name("Mogi das Cruzes")
            .build();
    }

    public City getPersistedCity() {
        return repository.save(this.getDefaultCity());
    }
}
