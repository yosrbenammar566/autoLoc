package tn.esprit.autoloc.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.autoloc.service.IReservation;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservation {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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
}
