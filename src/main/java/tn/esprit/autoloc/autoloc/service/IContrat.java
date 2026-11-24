package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.dto.ContratDTO;

import java.time.LocalDate;
import java.util.List;

public interface IContrat {
    List<Contrat> retrieveAllContrats();
    Contrat addContrat(Contrat contrat);
    Contrat updateContrat(Contrat contrat);
    Contrat retrieveContrat(Long idContrat);
    void removeContrat(Long idContrat);
    List<Contrat> addContrats(List<Contrat> contrats);
    ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin);
}
