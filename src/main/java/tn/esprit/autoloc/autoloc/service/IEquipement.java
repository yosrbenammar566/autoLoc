package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipement {
    List<Equipement> retrieveAllEquipements();
    Equipement addEquipement(Equipement equipement);
    Equipement updateEquipement(Equipement equipement);
    Equipement retrieveEquipement(Long idEquipement);
    void removeEquipement(Long idEquipement);
    List<Equipement> addEquipements(List<Equipement> equipements);
}
