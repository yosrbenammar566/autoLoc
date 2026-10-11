package tn.esprit.autoloc.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Agence;
import tn.esprit.autoloc.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.autoloc.service.IAgenceService;

import java.util.List;

@Service
public class AgenceServiceImpl implements IAgenceService {
    private final AgenceRepository agenceRepository;

    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public List<Agence> retrieveAllAgences() {

        return agenceRepository.findAll();
    }

    @Override
    public Agence addAgence(Agence agence) {

        return agenceRepository.save(agence);
    }

    @Override
    public Agence updateAgence(Agence agence) {

        return agenceRepository.save(agence);
    }

    @Override
    public Agence retrieveAgence(Long idAgence) {

        return agenceRepository.findById(idAgence).orElse(null);
    }

    @Override
    public void removeAgence(Long idAgence) {

        agenceRepository.deleteById(idAgence);
    }

    @Override
    public List<Agence> addAgences(List<Agence> agences) {

        return agenceRepository.saveAll(agences);
    }
}
