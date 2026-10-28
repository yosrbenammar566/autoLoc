package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Client;
import tn.esprit.autoloc.autoloc.dto.ClientCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ClientResponseDto;
import tn.esprit.autoloc.autoloc.dto.ClientUpdateRequestDto;

import java.util.List;

public final class ClientMapper {

    private ClientMapper() {
    }

    public static ClientResponseDto toResponseDto(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientResponseDto(
                client.getIdClient(),
                client.getNom(),
                client.getPrenom(),
                client.getEmail(),
                client.getTelephone(),
                client.getNumPermis(),
                client.getDateInscription()
        );
    }

    public static List<ClientResponseDto> toResponseDtoList(List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::toResponseDto)
                .toList();
    }

    public static Client toEntity(ClientCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumPermis(dto.getNumPermis());
        client.setDateInscription(dto.getDateInscription());
        return client;
    }

    public static void updateEntityFromDto(ClientUpdateRequestDto dto, Client client) {
        if (dto == null || client == null) {
            return;
        }
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setNumPermis(dto.getNumPermis());
        client.setDateInscription(dto.getDateInscription());
    }
}
