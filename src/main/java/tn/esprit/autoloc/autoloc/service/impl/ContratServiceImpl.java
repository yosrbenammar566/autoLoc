package tn.esprit.autoloc.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.autoloc.service.IContrat;

import java.util.List;

@Service
public class ContratServiceImpl implements IContrat {
    private final ContratRepository contratRepository;

    public ContratServiceImpl(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public List<Contrat> retrieveAllContrats() {

        return contratRepository.findAll();
    }

    @Override
    public Contrat addContrat(Contrat contrat) {

        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(Contrat contrat) {

        return contratRepository.save(contrat);
    }

    @Override
    public Contrat retrieveContrat(Long idContrat) {

        return contratRepository.findById(idContrat).orElse(null);
    }

    @Override
    public void removeContrat(Long idContrat) {

        contratRepository.deleteById(idContrat);
    }

    @Override
    public List<Contrat> addContrats(List<Contrat> contrats) {

        return contratRepository.saveAll(contrats);
    }
}
