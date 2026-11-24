package tn.esprit.autoloc.autoloc.mapper;

import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.domain.Paiement;
import tn.esprit.autoloc.autoloc.dto.PaiementCreateRequestDto;
import tn.esprit.autoloc.autoloc.dto.PaiementResponseDto;
import tn.esprit.autoloc.autoloc.dto.PaiementUpdateRequestDto;

import java.util.List;

public final class PaiementMapper {

    private PaiementMapper() {
    }

    public static PaiementResponseDto toResponseDto(Paiement paiement) {
        if (paiement == null) {
            return null;
        }
        return new PaiementResponseDto(
                paiement.getIdPaiement(),
                paiement.getMontant(),
                paiement.getDatePaiement(),
                paiement.getModePaiement(),
                paiement.getContrat() == null ? null : paiement.getContrat().getIdContrat()
        );
    }

    public static List<PaiementResponseDto> toResponseDtoList(List<Paiement> paiements) {
        return paiements.stream()
                .map(PaiementMapper::toResponseDto)
                .toList();
    }

    public static Paiement toEntity(PaiementCreateRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Paiement paiement = new Paiement();
        paiement.setMontant(dto.getMontant());
        paiement.setDatePaiement(dto.getDatePaiement());
        paiement.setModePaiement(dto.getModePaiement());
        paiement.setContrat(contratReference(dto.getContratId()));
        return paiement;
    }

    public static void updateEntityFromDto(PaiementUpdateRequestDto dto, Paiement paiement) {
        if (dto == null || paiement == null) {
            return;
        }
        paiement.setMontant(dto.getMontant());
        paiement.setDatePaiement(dto.getDatePaiement());
        paiement.setModePaiement(dto.getModePaiement());
        paiement.setContrat(contratReference(dto.getContratId()));
    }

    private static Contrat contratReference(Long contratId) {
        if (contratId == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setIdContrat(contratId);
        return contrat;
    }
}