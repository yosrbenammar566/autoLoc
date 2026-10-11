package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Maintenance;
import tn.esprit.autoloc.autoloc.service.IMaintenance;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    private final IMaintenance maintenanceService;

    public MaintenanceController(IMaintenance maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.retrieveAllMaintenances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.retrieveMaintenance(id);
        if (maintenance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maintenance);
    }

    @PostMapping
    public Maintenance addMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.addMaintenance(maintenance);
    }

    @PostMapping("/batch")
    public List<Maintenance> addMaintenances(@RequestBody List<Maintenance> maintenances) {
        return maintenanceService.addMaintenances(maintenances);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        if (maintenanceService.retrieveMaintenance(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenance.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.updateMaintenance(maintenance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        if (maintenanceService.retrieveMaintenance(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenanceService.removeMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
