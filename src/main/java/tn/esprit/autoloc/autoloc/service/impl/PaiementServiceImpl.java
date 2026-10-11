package tn.esprit.autoloc.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Paiement;
import tn.esprit.autoloc.autoloc.repository.PaiementRepository;
import tn.esprit.autoloc.autoloc.service.IPaiement;

import java.util.List;

@Service
public class PaiementServiceImpl implements IPaiement {
    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> retrieveAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement retrievePaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement).orElse(null);
    }

    @Override
    public void removePaiement(Long idPaiement) {
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public List<Paiement> addPaiements(List<Paiement> paiements) {
        return paiementRepository.saveAll(paiements);
    }
}
