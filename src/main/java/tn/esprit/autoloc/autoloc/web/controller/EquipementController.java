package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Equipement;
import tn.esprit.autoloc.autoloc.service.IEquipement;

import java.util.List;

@RestController
@RequestMapping("/equipements")
public class EquipementController {

    private final IEquipement equipementService;

    public EquipementController(IEquipement equipementService) {
        this.equipementService = equipementService;
    }

    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        Equipement equipement = equipementService.retrieveEquipement(id);
        if (equipement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipement);
    }

    @PostMapping
    public Equipement addEquipement(@RequestBody Equipement equipement) {
        return equipementService.addEquipement(equipement);
    }

    @PostMapping("/batch")
    public List<Equipement> addEquipements(@RequestBody List<Equipement> equipements) {
        return equipementService.addEquipements(equipements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        if (equipementService.retrieveEquipement(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipement.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.updateEquipement(equipement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        if (equipementService.retrieveEquipement(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipementService.removeEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
