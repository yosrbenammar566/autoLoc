package tn.esprit.autoloc.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autoloc.domain.Contrat;

import java.util.Optional;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    Optional<Contrat> findByReservation_IdReservation(Long idReservation);
}
