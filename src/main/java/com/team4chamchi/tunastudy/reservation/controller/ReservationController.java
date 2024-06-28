package com.team4chamchi.tunastudy.reservation.controller;

import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.service.ReservationService;
import com.team4chamchi.tunastudy.studyroom.dto.StudyRoomDTO;
import com.team4chamchi.tunastudy.studyroom.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final StudyRoomService studyRoomService;

    @Autowired
    public ReservationController(ReservationService reservationService, StudyRoomService studyRoomService) {
        this.reservationService = reservationService;
        this.studyRoomService = studyRoomService;
    }

    @GetMapping("/reservation/{phone}/{roomId}")
    public ResponseEntity<ReservationDTO> addReservation(@PathVariable("phone") String phone, @PathVariable("roomId") int roomId) {
        ReservationDTO reservation = reservationService.AddReservationByPhoneAndSeat(phone, roomId);

        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/seat")
    public ResponseEntity<List<ReservationDTO>> findAllAvailableSeat() {
        List<ReservationDTO> reservationList = reservationService.findAllAvailableSeat();

        return ResponseEntity.ok(reservationList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudyRoomDTO>> findAllSeat() {
        List<StudyRoomDTO> allSeatList = studyRoomService.findALlSeat();

        return ResponseEntity.ok(allSeatList);
    }
}
