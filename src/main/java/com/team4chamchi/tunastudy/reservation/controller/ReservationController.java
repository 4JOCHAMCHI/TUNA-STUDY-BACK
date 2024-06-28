package com.team4chamchi.tunastudy.reservation.controller;

import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{phone}/{roomId}")
    public ResponseEntity<ReservationDTO> addReservation(@PathVariable("phone") String phone, @PathVariable("roomId") int roomId) {
        ReservationDTO reservation = reservationService.AddReservationByPhoneAndSeat(phone, roomId);

        return ResponseEntity.ok(reservation);
    }
}
