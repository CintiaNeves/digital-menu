package br.com.digitalmenu.controller;

import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.request.CityRequest;
import br.com.digitalmenu.domain.response.CityResponse;
import br.com.digitalmenu.exception.NotFoundException;
import br.com.digitalmenu.mapper.CityMapper;
import br.com.digitalmenu.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService service;

    private final CityMapper cityMapper;

    @PostMapping
    public ResponseEntity<CityResponse> create(@Valid @RequestBody CityRequest cityRequest){
        var city = service.save(cityMapper.cityFromRequest(cityRequest));

        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{cityId}")
                .buildAndExpand(city.getId()).toUri();

        return ResponseEntity.created(uri).body(cityMapper.cityToCityResponse(city));
    }

    @GetMapping
    public ResponseEntity<List<CityResponse>> findAll() {
        return ResponseEntity.ok(cityMapper.cityToCityResponse(service.findAll()));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findById(@PathVariable Long cityId) {
        var city = service.findById(cityId)
                .orElseThrow(()-> new NotFoundException(String.format("City with id %s not found.", cityId)));

        return ResponseEntity.ok(city);
    }

}