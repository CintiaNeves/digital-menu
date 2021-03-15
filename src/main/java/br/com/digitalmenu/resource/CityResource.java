package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.CityRequest;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityResource {

    @Autowired
    private CityService service;

    @GetMapping
    public List<City> findAll () {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City create (@Valid @RequestBody CityRequest cityRequest){
        return service.save(cityRequest);
    }
}