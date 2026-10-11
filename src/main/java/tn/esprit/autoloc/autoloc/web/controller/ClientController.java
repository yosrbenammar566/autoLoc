package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Client;
import tn.esprit.autoloc.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.retrieveClient(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PostMapping("/batch")
    public List<Client> addClients(@RequestBody List<Client> clients) {
        return clientService.addClients(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        if (clientService.retrieveClient(id) == null) {
            return ResponseEntity.notFound().build();
        }
        client.setIdClient(id);
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.retrieveClient(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.removeClient(id);
        return ResponseEntity.noContent().build();
    }
}
