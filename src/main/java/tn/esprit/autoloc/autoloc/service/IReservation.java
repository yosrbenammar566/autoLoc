package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autoloc.dto.ReservationWithContratRequestDTO;

import java.util.List;

public interface IReservation {
    List<Reservation> retrieveAllReservations();
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    Reservation retrieveReservation(Long idReservation);
    void removeReservation(Long idReservation);
    List<Reservation> addReservations(List<Reservation> reservations);
    ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom);
    ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO);
}
