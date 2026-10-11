package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.service.IVehicule;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {

    private final IVehicule vehiculeService;

    public VehiculeController(IVehicule vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return vehiculeService.retrieveAllVehicules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.retrieveVehicule(id);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicule);
    }

    @PostMapping
    public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.addVehicule(vehicule);
    }

    @PostMapping("/batch")
    public List<Vehicule> addVehicules(@RequestBody List<Vehicule> vehicules) {
        return vehiculeService.addVehicules(vehicules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        if (vehiculeService.retrieveVehicule(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehicule.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.updateVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        if (vehiculeService.retrieveVehicule(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.removeVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
