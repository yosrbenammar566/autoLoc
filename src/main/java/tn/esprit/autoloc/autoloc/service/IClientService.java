package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.dto.ClientCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ClientResponseDto;
import tn.esprit.autoloc.autoloc.dto.ClientUpdateRequestDto;

import java.util.List;

public interface IClientService {
    List<ClientResponseDto> retrieveAllClients();
    ClientResponseDto addClient(ClientCreateRequestDto c);
    ClientResponseDto updateClient(Long idClient, ClientUpdateRequestDto c);
    ClientResponseDto retrieveClient(Long idClient);
    void removeClient(Long idClient);
    List<ClientResponseDto> addClients(List<ClientCreateRequestDto> clients);

}
