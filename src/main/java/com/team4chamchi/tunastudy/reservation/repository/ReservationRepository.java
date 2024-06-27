package com.team4chamchi.tunastudy.reservation.repository;

import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //전화번호로 조회
     Optional<Reservation> findByMember_MemberPhone(String memberPhone);
}
