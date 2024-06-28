package com.team4chamchi.tunastudy.reservation.service;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import com.team4chamchi.tunastudy.member.repository.MemberRepository;
import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.repository.ReservationRepository;
import com.team4chamchi.tunastudy.studyroom.aggregate.StudyRoom;
import com.team4chamchi.tunastudy.studyroom.respository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final StudyRoomRepository studyRoomRepository;
    private MemberRepository memberRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(MemberRepository memberRepository, ReservationRepository reservationRepository, StudyRoomRepository studyRoomRepository) {
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.studyRoomRepository = studyRoomRepository;
    }

    public List<ReservationDTO> findAllAvailableSeat() {
        return reservationRepository.findByOccupiedFalse().stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    public Member findMemberByPhone(String memberPhone) {
        Optional<Member> member = memberRepository.findByMemberPhone(memberPhone);

        return member.orElseGet(() -> {
            //조회해서 멤버가 없으면 생성
            Member newMember = new Member(memberPhone);
            return memberRepository.save(newMember);
        });
    }

    @Transactional
    public ReservationDTO AddReservationByPhoneAndSeat(String memberPhone, int roomId) {
        Member member = findMemberByPhone(memberPhone);
        StudyRoom room = studyRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("유효하지 않은 좌석입니다."));

        //전화번호랑 좌석으로 예약 조회
        Optional<Reservation> reservation = reservationRepository.findByMember_MemberPhoneAndRoom_RoomId(memberPhone, roomId);

        //조회된 예약이 없는 경우 -> 예약
        if (reservation.isEmpty()) {
            Reservation newReservation = new Reservation(member, room);
            reservationRepository.save(newReservation);

            return new ReservationDTO(reservationRepository.save(newReservation));
        }

        //조회된 예약이 있는데 상태가 False -> 예약
        if (!reservation.get().getOccupied()) {
            reservation.get().setOccupied(true);
        } else {
            //조회된 예약이 있는데 상태가 True -> 퇴실
            reservation.get().setOccupied(false);
        }

        return new ReservationDTO(reservationRepository.save(reservation.get()));
    }
}
