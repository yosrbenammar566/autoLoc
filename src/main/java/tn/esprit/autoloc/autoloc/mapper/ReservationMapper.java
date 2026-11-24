package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Client;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.ReservationCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ReservationResponseDto;
import tn.esprit.autoloc.autoloc.dto.ReservationUpdateRequestDto;

import java.util.List;

public final class ReservationMapper {

    private ReservationMapper() {
    }

    public static ReservationResponseDto toResponseDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        return new ReservationResponseDto(
                reservation.getIdReservation(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getStatut(),
                reservation.getVehicule() == null ? null : reservation.getVehicule().getIdVehicule(),
                reservation.getClient() == null ? null : reservation.getClient().getIdClient()
        );
    }

    public static List<ReservationResponseDto> toResponseDtoList(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationMapper::toResponseDto)
                .toList();
    }

    public static Reservation toEntity(ReservationCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        reservation.setVehicule(vehiculeReference(dto.getVehiculeId()));
        reservation.setClient(clientReference(dto.getClientId()));
        return reservation;
    }

    public static void updateEntityFromDto(ReservationUpdateRequestDto dto, Reservation reservation) {
        if (dto == null || reservation == null) {
            return;
        }
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        reservation.setVehicule(vehiculeReference(dto.getVehiculeId()));
        reservation.setClient(clientReference(dto.getClientId()));
    }

    private static Vehicule vehiculeReference(Long vehiculeId) {
        if (vehiculeId == null) {
            return null;
        }
        Vehicule vehicule = new Vehicule();
        vehicule.setIdVehicule(vehiculeId);
        return vehicule;
    }

    private static Client clientReference(Long clientId) {
        if (clientId == null) {
            return null;
        }
        Client client = new Client();
        client.setIdClient(clientId);
        return client;
    }
}