package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Paiement;
import tn.esprit.autoloc.autoloc.service.IPaiement;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {

    private final IPaiement paiementService;

    public PaiementController(IPaiement paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.retrieveAllPaiements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        Paiement paiement = paiementService.retrievePaiement(id);
        if (paiement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paiement);
    }

    @PostMapping
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @PostMapping("/batch")
    public List<Paiement> addPaiements(@RequestBody List<Paiement> paiements) {
        return paiementService.addPaiements(paiements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        if (paiementService.retrievePaiement(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiement.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.updatePaiement(paiement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        if (paiementService.retrievePaiement(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiementService.removePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
