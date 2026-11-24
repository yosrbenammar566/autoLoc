package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Maintenance;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.MaintenanceCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.MaintenanceResponseDto;
import tn.esprit.autoloc.autoloc.dto.MaintenanceUpdateRequestDto;

import java.util.List;

public final class MaintenanceMapper {

    private MaintenanceMapper() {
    }

    public static MaintenanceResponseDto toResponseDto(Maintenance maintenance) {
        if (maintenance == null) {
            return null;
        }
        return new MaintenanceResponseDto(
                maintenance.getIdMaintenance(),
                maintenance.getDateDebut(),
                maintenance.getDateFin(),
                maintenance.getDescription(),
                maintenance.getVehicule() == null ? null : maintenance.getVehicule().getIdVehicule()
        );
    }

    public static List<MaintenanceResponseDto> toResponseDtoList(List<Maintenance> maintenances) {
        return maintenances.stream()
                .map(MaintenanceMapper::toResponseDto)
                .toList();
    }

    public static Maintenance toEntity(MaintenanceCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());
        maintenance.setVehicule(vehiculeReference(dto.getVehiculeId()));
        return maintenance;
    }

    public static void updateEntityFromDto(MaintenanceUpdateRequestDto dto, Maintenance maintenance) {
        if (dto == null || maintenance == null) {
            return;
        }
        maintenance.setDateDebut(dto.getDateDebut());
        maintenance.setDateFin(dto.getDateFin());
        maintenance.setDescription(dto.getDescription());
        maintenance.setVehicule(vehiculeReference(dto.getVehiculeId()));
    }

    private static Vehicule vehiculeReference(Long vehiculeId) {
        if (vehiculeId == null) {
            return null;
        }
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(vehiculeId);
        return vehicule;
    }
}