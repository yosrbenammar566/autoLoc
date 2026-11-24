package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.dto.ContratCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.autoloc.dto.ContratUpdateRequestDto;

import java.util.List;

public final class ContratMapper {

    private ContratMapper() {
    }

    public static ContratDTO toResponseDto(Contrat contrat) {
        if (contrat == null) {
            return null;
        }
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateSignature(),
                contrat.getMontantTotal(),
                contrat.isValide(),
                contrat.getReservation() == null ? null : contrat.getReservation().getIdReservation()
        );
    }

    public static List<ContratDTO> toResponseDtoList(List<Contrat> contrats) {
        return contrats.stream()
                .map(ContratMapper::toResponseDto)
                .toList();
    }

    public static Contrat toEntity(ContratCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
        contrat.setReservation(reservationReference(dto.getReservationId()));
        return contrat;
    }

    public static void updateEntityFromDto(ContratUpdateRequestDto dto, Contrat contrat) {
        if (dto == null || contrat == null) {
            return;
        }
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setMontantTotal(dto.getMontantTotal());
        contrat.setValide(dto.isValide());
        contrat.setReservation(reservationReference(dto.getReservationId()));
    }

    private static Reservation reservationReference(Long reservationId) {
        if (reservationId == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setIdReservation(reservationId);
        return reservation;
    }
}