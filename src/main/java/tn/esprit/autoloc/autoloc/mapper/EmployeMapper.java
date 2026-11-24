package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Agence;
import tn.esprit.autoloc.autoloc.domain.Employe;
import tn.esprit.autoloc.autoloc.dto.EmployeCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.EmployeResponseDto;
import tn.esprit.autoloc.autoloc.dto.EmployeUpdateRequestDto;

import java.util.List;

public final class EmployeMapper {

    private EmployeMapper() {
    }

    public static EmployeResponseDto toResponseDto(Employe employe) {
        if (employe == null) {
            return null;
        }
        return new EmployeResponseDto(
                employe.getIdEmploye(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getRole(),
                employe.getAgence() == null ? null : employe.getAgence().getIdAgence()
        );
    }

    public static List<EmployeResponseDto> toResponseDtoList(List<Employe> employes) {
        return employes.stream()
                .map(EmployeMapper::toResponseDto)
                .toList();
    }

    public static Employe toEntity(EmployeCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setRole(dto.getRole());
        employe.setAgence(agenceReference(dto.getAgenceId()));
        return employe;
    }

    public static void updateEntityFromDto(EmployeUpdateRequestDto dto, Employe employe) {
        if (dto == null || employe == null) {
            return;
        }
        employe.setNom(dto.getNom());
        employe.setPrenom(dto.getPrenom());
        employe.setRole(dto.getRole());
        employe.setAgence(agenceReference(dto.getAgenceId()));
    }

    private static Agence agenceReference(Long agenceId) {
        if (agenceId == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setIdAgence(agenceId);
        return agence;
    }
}