package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenance {
    List<Maintenance> retrieveAllMaintenances();
    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    Maintenance retrieveMaintenance(Long idMaintenance);
    void removeMaintenance(Long idMaintenance);
    List<Maintenance> addMaintenances(List<Maintenance> maintenances);
}
