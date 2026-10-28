package tn.esprit.autoloc.autoloc.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.dto.ClientCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ClientResponseDto;
import tn.esprit.autoloc.autoloc.dto.ClientUpdateRequestDto;
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
    public List<ClientResponseDto> getAllClients() {
        return clientService.retrieveAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id) {
        ClientResponseDto client = clientService.retrieveClient(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ClientResponseDto addClient(@Valid @RequestBody ClientCreateRequestDto client) {
        return clientService.addClient(client);
    }

    @PostMapping("/batch")
    public List<ClientResponseDto> addClients(@Valid @RequestBody List<@Valid ClientCreateRequestDto> clients) {
        return clientService.addClients(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientUpdateRequestDto client) {
        if (clientService.retrieveClient(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientService.updateClient(id, client));
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
