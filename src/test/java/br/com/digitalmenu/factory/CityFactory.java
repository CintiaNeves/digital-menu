package br.com.digitalmenu.factory;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityFactory {

    @Autowired
    private CityRepository repository;

    public City getDefaultCity() {
        City city = new City();
        city.setName("Mogi das Cruzes");
        return city;
    }

    public City getEmptyCity() {
        return new City();
    }

    public City getPersistedCity() {
        return repository.save(this.getDefaultCity());
    }
}
