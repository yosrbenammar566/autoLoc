package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.autoloc.domain.StatutReservation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUpdateRequestDto {

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @NotNull
    private StatutReservation statut;

    @NotNull
    private Long vehiculeId;

    @NotNull
    private Long clientId;
}
