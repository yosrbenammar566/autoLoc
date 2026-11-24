package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Equipement;
import tn.esprit.autoloc.autoloc.dto.EquipementCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.EquipementResponseDto;
import tn.esprit.autoloc.autoloc.dto.EquipementUpdateRequestDto;

import java.util.List;

public final class EquipementMapper {

    private EquipementMapper() {
    }

    public static EquipementResponseDto toResponseDto(Equipement equipement) {
        if (equipement == null) {
            return null;
        }
        return new EquipementResponseDto(equipement.getIdEquipement(), equipement.getLibelle());
    }

    public static List<EquipementResponseDto> toResponseDtoList(List<Equipement> equipements) {
        return equipements.stream()
                .map(EquipementMapper::toResponseDto)
                .toList();
    }

    public static Equipement toEntity(EquipementCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Equipement equipement = new Equipement();
        equipement.setLibelle(dto.getLibelle());
        return equipement;
    }

    public static void updateEntityFromDto(EquipementUpdateRequestDto dto, Equipement equipement) {
        if (dto == null || equipement == null) {
            return;
        }
        equipement.setLibelle(dto.getLibelle());
    }
}