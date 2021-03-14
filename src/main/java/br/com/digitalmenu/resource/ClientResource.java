package br.com.digitalmenu.resource;

import br.com.digitalmenu.domain.entity.Client;
import br.com.digitalmenu.domain.request.ClientRequest;
import br.com.digitalmenu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll (){
        return service.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findById (@PathVariable Long clientId){
        Optional<Client> client = service.findById(clientId);

        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create (@Valid @RequestBody ClientRequest clientRequest){
        return service.save(clientRequest);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update (@PathVariable Long clientId, @Valid @RequestBody ClientRequest clientRequest){

        if(!service.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(clientId, clientRequest));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete (@Valid @PathVariable Long clientId){

        if(!service.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }
        service.delete(clientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{clientName}")
    public List<Client> findLikeName (@PathVariable String clientName){
        return service.findLikeName(clientName);
    }
}