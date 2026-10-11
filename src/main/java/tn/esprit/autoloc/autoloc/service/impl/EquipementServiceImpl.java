package tn.esprit.autoloc.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Equipement;
import tn.esprit.autoloc.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.autoloc.service.IEquipement;

import java.util.List;

@Service
public class EquipementServiceImpl implements IEquipement {
    private final EquipementRepository equipementRepository;

    public EquipementServiceImpl(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public List<Equipement> retrieveAllEquipements() {

        return equipementRepository.findAll();
    }

    @Override
    public Equipement addEquipement(Equipement equipement) {

        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {

        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement retrieveEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement).orElse(null);
    }

    @Override
    public void removeEquipement(Long idEquipement) {

        equipementRepository.deleteById(idEquipement);
    }

    @Override
    public List<Equipement> addEquipements(List<Equipement> equipements) {
        return equipementRepository.saveAll(equipements);
    }
}
