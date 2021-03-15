package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Address;
import br.com.digitalmenu.domain.entity.City;
import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.exception.EntityNotFoundException;
import br.com.digitalmenu.repository.ClientRepository;
import br.com.digitalmenu.service.CityService;
import br.com.digitalmenu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private CityService service;


    @Override
    public Client save(ClientRequest clientRequest) {
        if (repository.findByPhone(clientRequest.getPhone()).isPresent()) {
            throw new EntityAlreadyExistsException("Entity already exists.");
        }
        Client client = new Client();
        buildClient(clientRequest, client);
        return repository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> findById(Long clientId) {
        return repository.findById(clientId);
    }

    @Override
    public Client update(Long clientId, ClientRequest clientRequest) {
        Client client = repository.findById(clientId).get();
        buildClient(clientRequest, client);

        return repository.save(client);
    }

    @Override
    public Boolean existsById(Long clientId) {
        return repository.existsById(clientId);
    }

    @Override
    public void delete(Long clientId) {
        Client client = repository.findById(clientId).get();
        repository.delete(client);
    }

    @Override
    public Optional<Client> findByName(String clientName) {
        return repository.findByName(clientName);
    }

    @Override
    public List<Client> findLikeName(String clientName) {
        return repository.findByNameContaining(clientName);
    }

    private void buildClient(ClientRequest clientRequest, Client client) {
        nameFormatter(clientRequest.getName());
        client.setName(nameFormatter(clientRequest.getName()));
        client.setPhone(clientRequest.getPhone());
        client.setEmail(clientRequest.getEmail().toLowerCase());
        Set<Address> addressList = new HashSet<>();

        for(var addressRequest : clientRequest.getAddressList()){
            Address address = new Address();
            City city = service.findById(addressRequest.getCityId())
                    .orElseThrow(() -> new EntityNotFoundException("Entity City is not found"));
            address.setCity(city);
            address.setAddressName(addressRequest.getAddressName());
            address.setNumber(addressRequest.getNumber());
            address.setPostalArea(addressRequest.getPostalArea());
            addressList.add(address);
        }
        client.setAddressList(addressList);
    }

    private String nameFormatter(String name){
        name = name.toLowerCase();
        String names[] = name.split(" ");
        List<String> listName = Arrays.asList(names);
        String nameFormatted = "";

        for(var n : listName){
            if(n.length() > 3){
                n = n.substring(0,1).toUpperCase().concat(n.substring(1));
            }
            nameFormatted = nameFormatted.concat(n).concat(" ");
        }
        return nameFormatted.trim();
    }
}