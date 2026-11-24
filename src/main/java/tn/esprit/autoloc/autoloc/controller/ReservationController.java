package tn.esprit.autoloc.autoloc.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.autoloc.service.IReservation;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservation reservationService;

    public ReservationController(IReservation reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Affecte une réservation existante à un client identifié par son nom et prénom.
     *
     * PUT /api/reservations/{idReservation}/client?nom=Doe&prenom=John
     */
    @PutMapping("/{idReservation}/client")
    public ResponseEntity<ReservationResponseDTO> affecterReservationAClient(
            @PathVariable Long idReservation,
            @RequestParam String nom,
            @RequestParam String prenom) {
        ReservationResponseDTO result = reservationService.affecterReservationAClient(idReservation, nom, prenom);
        return ResponseEntity.ok(result);
    }

    /**
     * Crée une nouvelle réservation avec son contrat associé.
     *
     * POST /api/reservations/avec-contrat
     */
    @PostMapping("/avec-contrat")
    public ResponseEntity<ReservationResponseDTO> creerReservationEtContratAssocie(
            @Valid @RequestBody ReservationWithContratRequestDTO requestDTO) {
        ReservationResponseDTO result = reservationService.creerReservationEtContratAssocie(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
