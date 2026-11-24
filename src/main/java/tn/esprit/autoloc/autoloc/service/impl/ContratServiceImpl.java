package tn.esprit.autoloc.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.autoloc.mapper.ContratMapper;
import tn.esprit.autoloc.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.autoloc.service.IContrat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContratServiceImpl implements IContrat {
    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    public ContratServiceImpl(ContratRepository contratRepository, ReservationRepository reservationRepository) {
        this.contratRepository = contratRepository;
        this.reservationRepository = reservationRepository;
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

    @Override
    @Transactional
    public ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin) {
        if (dateSignature == null || dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates de signature et de réservation sont obligatoires.");
        }
        if (!dateFin.isAfter(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
        }
        List<Reservation> reservations = reservationRepository.findByDateDebutAndDateFin(dateDebut, dateFin);
        if (reservations.isEmpty()) {
            throw new EntityNotFoundException("Aucune réservation ne correspond aux dates fournies.");
        }
        if (reservations.size() > 1) {
            throw new IllegalStateException("Plusieurs réservations correspondent aux dates fournies.");
        }

        Reservation reservation = reservations.get(0);
        if (contratRepository.findByReservation_IdReservation(reservation.getIdReservation()).isPresent()) {
            throw new IllegalStateException("Cette réservation possède déjà un contrat.");
        }

        Contrat contrat = new Contrat();
        contrat.setDateSignature(dateSignature);
        long duree = ChronoUnit.DAYS.between(dateDebut, dateFin);
        BigDecimal tarifJournalier = reservation.getVehicule().getTarifJournalier();
        contrat.setMontantTotal(tarifJournalier.multiply(BigDecimal.valueOf(duree)));
        contrat.setValide(true);
        contrat.setReservation(reservation);
        reservation.setContrat(contrat);
        return ContratMapper.toResponseDto(contratRepository.save(contrat));
    }
}
