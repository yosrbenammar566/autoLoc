package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratUpdateRequestDto {

    @NotNull
    private LocalDate dateSignature;

    @NotNull
    @PositiveOrZero
    private BigDecimal montantTotal;

    private boolean valide;

    @NotNull
    private Long reservationId;
}
