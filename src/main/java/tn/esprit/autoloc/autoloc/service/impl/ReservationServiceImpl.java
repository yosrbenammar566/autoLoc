package tn.esprit.autoloc.autoloc.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autoloc.domain.Client;
import tn.esprit.autoloc.autoloc.domain.Contrat;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.autoloc.domain.Vehicule;
import tn.esprit.autoloc.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.autoloc.mapper.ReservationMapper;
import tn.esprit.autoloc.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.autoloc.service.IReservation;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservation {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ContratRepository contratRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ClientRepository clientRepository,
                                  VehiculeRepository vehiculeRepository,
                                  ContratRepository contratRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.contratRepository = contratRepository;
    }

    @Override
    public List<Reservation> retrieveAllReservations() {

        return reservationRepository.findAll();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation retrieveReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public void removeReservation(Long idReservation) {

        reservationRepository.deleteById(idReservation);
    }

    @Override
    public List<Reservation> addReservations(List<Reservation> reservations) {
        return reservationRepository.saveAll(reservations);
    }

    @Override
    @Transactional
    public ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));
        List<Client> clients = clientRepository.findByNomAndPrenom(nom, prenom);
        if (clients.isEmpty()) {
            throw new EntityNotFoundException("Client introuvable : " + prenom + " " + nom);
        }
        if (clients.size() > 1) {
            throw new IllegalStateException("Plusieurs clients correspondent au nom et prénom fournis.");
        }

        reservation.setClient(clients.get(0));
        return ReservationMapper.toResponseDto(reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("La demande de réservation ne peut pas être nulle.");
        }
        if (requestDTO.getDateDebut() == null || requestDTO.getDateFin() == null
                || requestDTO.getDateSignature() == null
                || requestDTO.getVehiculeId() == null || requestDTO.getClientId() == null) {
            throw new IllegalArgumentException("Les dates ainsi que les identifiants du véhicule et du client sont obligatoires.");
        }
        if (!requestDTO.getDateFin().isAfter(requestDTO.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
        }

        Vehicule vehicule = vehiculeRepository.findById(requestDTO.getVehiculeId())
                .orElseThrow(() -> new EntityNotFoundException("Véhicule introuvable : " + requestDTO.getVehiculeId()));
        Client client = clientRepository.findById(requestDTO.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + requestDTO.getClientId()));

        Reservation reservation = new Reservation();
        reservation.setDateDebut(requestDTO.getDateDebut());
        reservation.setDateFin(requestDTO.getDateFin());
        reservation.setStatut(StatutReservation.EN_ATTENTE);
        reservation.setVehicule(vehicule);
        reservation.setClient(client);
        reservation = reservationRepository.save(reservation);

        Contrat contrat = new Contrat();
        contrat.setDateSignature(requestDTO.getDateSignature());
        contrat.setMontantTotal(vehicule.getTarifJournalier().multiply(
                BigDecimal.valueOf(ChronoUnit.DAYS.between(requestDTO.getDateDebut(), requestDTO.getDateFin()))));
        contrat.setValide(true);
        contrat.setReservation(reservation);
        reservation.setContrat(contrat);
        contratRepository.save(contrat);

        return ReservationMapper.toResponseDto(reservation);
    }
}
