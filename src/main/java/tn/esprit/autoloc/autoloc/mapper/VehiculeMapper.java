package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Agence;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.VehiculeCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.VehiculeResponseDto;
import tn.esprit.autoloc.autoloc.dto.VehiculeUpdateRequestDto;

import java.util.List;

public final class VehiculeMapper {

    private VehiculeMapper() {
    }

    public static VehiculeResponseDto toResponseDto(Vehicule vehicule) {
        if (vehicule == null) {
            return null;
        }
        return new VehiculeResponseDto(
                vehicule.getIdVehicule(),
                vehicule.getImmatriculation(),
                vehicule.getMarque(),
                vehicule.getModele(),
                vehicule.getCategorie(),
                vehicule.getTarifJournalier(),
                vehicule.getStatut(),
                vehicule.getAgence() == null ? null : vehicule.getAgence().getIdAgence()
        );
    }

    public static List<VehiculeResponseDto> toResponseDtoList(List<Vehicule> vehicules) {
        return vehicules.stream()
                .map(VehiculeMapper::toResponseDto)
                .toList();
    }

    public static Vehicule toEntity(VehiculeCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());
        vehicule.setAgence(agenceReference(dto.getAgenceId()));
        return vehicule;
    }

    public static void updateEntityFromDto(VehiculeUpdateRequestDto dto, Vehicule vehicule) {
        if (dto == null || vehicule == null) {
            return;
        }
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setStatut(dto.getStatut());
        vehicule.setAgence(agenceReference(dto.getAgenceId()));
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