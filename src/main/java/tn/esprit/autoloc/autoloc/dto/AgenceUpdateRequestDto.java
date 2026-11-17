package tn.esprit.autoloc.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgenceUpdateRequestDto {

    @NotBlank
    private String nom;

    @NotBlank
    private String ville;

    @NotBlank
    private String adresse;

    @NotBlank
    private String telephone;
}
