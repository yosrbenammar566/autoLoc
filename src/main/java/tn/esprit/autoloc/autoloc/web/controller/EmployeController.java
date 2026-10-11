package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Employe;
import tn.esprit.autoloc.autoloc.service.IEmploye;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final IEmploye employeService;

    public EmployeController(IEmploye employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Employe employe = employeService.retrieveEmploye(id);
        if (employe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employe);
    }

    @PostMapping
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    @PostMapping("/batch")
    public List<Employe> addEmployes(@RequestBody List<Employe> employes) {
        return employeService.addEmployes(employes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        if (employeService.retrieveEmploye(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employe.setIdEmploye(id);
        return ResponseEntity.ok(employeService.updateEmploye(employe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        if (employeService.retrieveEmploye(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employeService.removeEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
