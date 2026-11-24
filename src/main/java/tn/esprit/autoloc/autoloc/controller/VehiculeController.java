package tn.esprit.autoloc.autoloc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.autoloc.service.IVehicule;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehicule vehiculeService;

    public VehiculeController(IVehicule vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    /**
     * Affecte un équipement à un véhicule.
     *
     * PUT /api/vehicules/{idVehicule}/equipements/{idEquipement}
     */
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<VehiculeDTO> affecterVehiculeAEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {
        VehiculeDTO result = vehiculeService.affecterVehiculeAEquipement(idVehicule, idEquipement);
        return ResponseEntity.ok(result);
    }

    /**
     * Affecte une maintenance à un véhicule via description et immatriculation.
     *
     * PUT /api/vehicules/maintenance?description=Vidange&immatriculation=TN1234
     */
    @PutMapping("/maintenance")
    public ResponseEntity<VehiculeDTO> affecterMaintenanceAvehicule(
            @RequestParam String description,
            @RequestParam String immatriculation) {
        VehiculeDTO result = vehiculeService.affecterMaintenanceAvehicule(description, immatriculation);
        return ResponseEntity.ok(result);
    }
}
