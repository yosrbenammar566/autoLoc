package tn.esprit.autoloc.autoloc.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autoloc.domain.Reservation;
import tn.esprit.autoloc.autoloc.service.IReservation;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservation reservationService;

    public ReservationController(IReservation reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.retrieveReservation(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PostMapping("/batch")
    public List<Reservation> addReservations(@RequestBody List<Reservation> reservations) {
        return reservationService.addReservations(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (reservationService.retrieveReservation(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservation.setIdReservation(id);
        return ResponseEntity.ok(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationService.retrieveReservation(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservationService.removeReservation(id);
        return ResponseEntity.noContent().build();
    }
}
