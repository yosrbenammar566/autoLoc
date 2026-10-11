package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.service.IContrat;

import java.util.List;

@RestController
@RequestMapping("/contrats")
public class ContratController {

    private final IContrat contratService;

    public ContratController(IContrat contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.retrieveAllContrats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        Contrat contrat = contratService.retrieveContrat(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrat);
    }

    @PostMapping
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @PostMapping("/batch")
    public List<Contrat> addContrats(@RequestBody List<Contrat> contrats) {
        return contratService.addContrats(contrats);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        if (contratService.retrieveContrat(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contrat.setIdContrat(id);
        return ResponseEntity.ok(contratService.updateContrat(contrat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        if (contratService.retrieveContrat(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contratService.removeContrat(id);
        return ResponseEntity.noContent().build();
    }
}
