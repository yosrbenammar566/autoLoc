package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.autoloc.domain.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaiementUpdateRequestDto {

    @NotNull
    @Positive
    private BigDecimal montant;

    @NotNull
    private LocalDate datePaiement;

    @NotNull
    private ModePaiement modePaiement;

    @NotNull
    private Long contratId;
}
