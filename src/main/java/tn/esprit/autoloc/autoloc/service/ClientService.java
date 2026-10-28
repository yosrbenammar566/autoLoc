package tn.esprit.autoloc.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Client;
import tn.esprit.autoloc.autoloc.dto.ClientCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ClientResponseDto;
import tn.esprit.autoloc.autoloc.dto.ClientUpdateRequestDto;
import tn.esprit.autoloc.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.autoloc.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService implements IClientService {

    private final ClientRepository repo;

    // Injection par constructeur
    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ClientResponseDto> retrieveAllClients() {
        return ClientMapper.toResponseDtoList(repo.findAll());
    }

    @Override
    public ClientResponseDto addClient(ClientCreateRequestDto c) {
        Client savedClient = repo.save(ClientMapper.toEntity(c));
        return ClientMapper.toResponseDto(savedClient);
    }

    @Override
    public ClientResponseDto updateClient(Long idClient, ClientUpdateRequestDto c) {
        Client existingClient = repo.findById(idClient).orElse(null);
        if (existingClient == null) {
            return null;
        }
        ClientMapper.updateEntityFromDto(c, existingClient);
        Client updatedClient = repo.save(existingClient);
        return ClientMapper.toResponseDto(updatedClient);
    }

    @Override
    public ClientResponseDto retrieveClient(Long idClient) {
        return ClientMapper.toResponseDto(repo.findById(idClient).orElse(null));
    }

    @Override
    public void removeClient(Long idClient) {
        repo.deleteById(idClient);
    }

    @Override
    public List<ClientResponseDto> addClients(List<ClientCreateRequestDto> clients) {
        List<Client> clientsToSave = clients.stream()
                .map(ClientMapper::toEntity)
                .toList();
        return ClientMapper.toResponseDtoList(repo.saveAll(clientsToSave));
    }
}