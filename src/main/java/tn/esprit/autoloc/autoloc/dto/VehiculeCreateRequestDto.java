package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeCreateRequestDto {

    @NotBlank
    private String immatriculation;

    @NotBlank
    private String marque;

    @NotBlank
    private String modele;

    @NotNull
    private CategorieVehicule categorie;

    @NotNull
    @PositiveOrZero
    private BigDecimal tarifJournalier;

    @NotNull
    private StatutVehicule statut;

    @NotNull
    private Long agenceId;
}
