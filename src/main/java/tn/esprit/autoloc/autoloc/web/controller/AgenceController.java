package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Agence;
import tn.esprit.autoloc.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    private final IAgenceService agenceService;

    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.retrieveAllAgences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable Long id) {
        Agence agence = agenceService.retrieveAgence(id);
        if (agence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agence);
    }

    @PostMapping
    public Agence addAgence(@RequestBody Agence agence) {
        return agenceService.addAgence(agence);
    }

    @PostMapping("/batch")
    public List<Agence> addAgences(@RequestBody List<Agence> agences) {
        return agenceService.addAgences(agences);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateAgence(@PathVariable Long id, @RequestBody Agence agence) {
        if (agenceService.retrieveAgence(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agence.setIdAgence(id);
        return ResponseEntity.ok(agenceService.updateAgence(agence));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        if (agenceService.retrieveAgence(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agenceService.removeAgence(id);
        return ResponseEntity.noContent().build();
    }
}
