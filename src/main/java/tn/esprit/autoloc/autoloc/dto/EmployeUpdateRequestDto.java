package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.autoloc.domain.RoleEmploye;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeUpdateRequestDto {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    private RoleEmploye role;

    @NotNull
    private Long agenceId;
}
