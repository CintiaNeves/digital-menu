package br.com.digitalmenu.service;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.ClientRequest;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    public Client save(ClientRequest clientRequest);

    public List<Client> findAll();

    public Optional<Client> findById(Long clientId);

    public Client update (Long clientId, ClientRequest clientRequest);

    public Boolean existsById(Long clientId);

    public void delete(Long clientId);

    public Optional<Client> findByName(String clientName);

    public List<Client> findLikeName(String clientName);
}
