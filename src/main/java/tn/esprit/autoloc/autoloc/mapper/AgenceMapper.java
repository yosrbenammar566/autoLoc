package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Agence;
import tn.esprit.autoloc.autoloc.dto.AgenceCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.AgenceResponseDto;
import tn.esprit.autoloc.autoloc.dto.AgenceUpdateRequestDto;

import java.util.List;

public final class AgenceMapper {

    private AgenceMapper() {
    }

    public static AgenceResponseDto toResponseDto(Agence agence) {
        if (agence == null) {
            return null;
        }
        return new AgenceResponseDto(
                agence.getIdAgence(),
                agence.getNom(),
                agence.getVille(),
                agence.getAdresse(),
                agence.getTelephone()
        );
    }

    public static List<AgenceResponseDto> toResponseDtoList(List<Agence> agences) {
        return agences.stream()
                .map(AgenceMapper::toResponseDto)
                .toList();
    }

    public static Agence toEntity(AgenceCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());
        return agence;
    }

    public static void updateEntityFromDto(AgenceUpdateRequestDto dto, Agence agence) {
        if (dto == null || agence == null) {
            return;
        }
        agence.setNom(dto.getNom());
        agence.setVille(dto.getVille());
        agence.setAdresse(dto.getAdresse());
        agence.setTelephone(dto.getTelephone());
    }
}
