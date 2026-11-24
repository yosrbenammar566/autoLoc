package tn.esprit.autoloc.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autoloc.domain.Equipement;
import tn.esprit.autoloc.autoloc.domain.Maintenance;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.autoloc.mapper.VehiculeMapper;
import tn.esprit.autoloc.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.autoloc.repository.MaintenanceRepository;
import tn.esprit.autoloc.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.autoloc.service.IVehicule;

import java.util.HashSet;
import java.util.List;

@Service
public class VehiculeServiceImpl implements IVehicule {
    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;
    private final MaintenanceRepository maintenanceRepository;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository,
                               EquipementRepository equipementRepository,
                               MaintenanceRepository maintenanceRepository) {

        this.vehiculeRepository = vehiculeRepository;
        this.equipementRepository = equipementRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Vehicule> retrieveAllVehicules() {

        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule retrieveVehicule(Long idVehicule) {

        return vehiculeRepository.findById(idVehicule).orElse(null);
    }

    @Override
    public void removeVehicule(Long idVehicule) {

        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public List<Vehicule> addVehicules(List<Vehicule> vehicules) {

        return vehiculeRepository.saveAll(vehicules);
    }

    @Override
    @Transactional
    public VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable : " + idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Équipement introuvable : " + idEquipement));
        if (vehicule.getEquipements() == null) {
            vehicule.setEquipements(new HashSet<>());
        }
        vehicule.getEquipements().add(equipement);
        return VehiculeMapper.toResponseDto(vehiculeRepository.save(vehicule));
    }

    @Override
    @Transactional
    public VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation) {
        Vehicule vehicule = vehiculeRepository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable : " + immatriculation));
        List<Maintenance> maintenances = maintenanceRepository.findByDescription(description);
        if (maintenances.isEmpty()) {
            throw new EntityNotFoundException("Maintenance introuvable : " + description);
        }
        if (maintenances.size() > 1) {
            throw new IllegalStateException("Plusieurs maintenances correspondent à la description fournie.");
        }

        Maintenance maintenance = maintenances.get(0);
        maintenance.setVehicule(vehicule);
        maintenanceRepository.save(maintenance);
        return VehiculeMapper.toResponseDto(vehicule);
    }
}
