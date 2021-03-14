package br.com.digitalmenu.service.impl;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import br.com.digitalmenu.repository.ClientRepository;
import br.com.digitalmenu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;


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
        client.setName(clientRequest.getName());
        client.setPhone(clientRequest.getPhone());
        client.setEmail(clientRequest.getEmail());
    }
}