package com.team4chamchi.tunastudy.reservation.repository;

import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

     Optional<Reservation> findByMember_MemberPhoneAndRoom_RoomId(String memberPhone, int roomId);
     List<Reservation> findByOccupiedFalse();
}
