package com.team4chamchi.tunastudy.reservation.service;

import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<ReservationDTO> getReservationsByMemberPhone(String memberPhone) {
        return reservationRepository.findByMember_MemberPhone(memberPhone).stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    public ResponseEntity
}
