package tn.esprit.autoloc.autoloc.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.autoloc.service.IContrat;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final IContrat contratService;

    public ContratController(IContrat contratService) {
        this.contratService = contratService;
    }

    /**
     * Affecte un contrat à une réservation identifiée par ses dates.
     *
     * POST /api/contrats/reservation?dateSignature=2024-01-01&dateDebut=2024-01-10&dateFin=2024-01-15
     */
    @PostMapping("/reservation")
    public ResponseEntity<ContratDTO> affecterContratAReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSignature,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        ContratDTO result = contratService.affecterContratAReservation(dateSignature, dateDebut, dateFin);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
