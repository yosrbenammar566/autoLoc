package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.VehiculeDTO;

import java.util.List;

public interface IVehicule {
    List<Vehicule> retrieveAllVehicules();
    Vehicule addVehicule(Vehicule vehicule);
    Vehicule updateVehicule(Vehicule vehicule);
    Vehicule retrieveVehicule(Long idVehicule);
    void removeVehicule(Long idVehicule);
    List<Vehicule> addVehicules(List<Vehicule> vehicules);
    VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement);
    VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation);
}
