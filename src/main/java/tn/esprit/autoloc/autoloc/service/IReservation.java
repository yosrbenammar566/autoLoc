package tn.esprit.autoloc.autoloc.service;

import tn.esprit.autoloc.autoloc.domain.Reservation;

import java.util.List;

public interface IReservation {
    List<Reservation> retrieveAllReservations();
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    Reservation retrieveReservation(Long idReservation);
    void removeReservation(Long idReservation);
    List<Reservation> addReservations(List<Reservation> reservations);
}
