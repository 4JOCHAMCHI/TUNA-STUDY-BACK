package com.team4chamchi.tunastudy.reservation.service;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import com.team4chamchi.tunastudy.member.repository.MemberRepository;
import com.team4chamchi.tunastudy.notification.service.NotificationService;
import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.repository.ReservationRepository;
import com.team4chamchi.tunastudy.studyroom.aggregate.StudyRoom;
import com.team4chamchi.tunastudy.studyroom.respository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final StudyRoomRepository studyRoomRepository;
    private final NotificationService notificationService;
    private MemberRepository memberRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(MemberRepository memberRepository, ReservationRepository reservationRepository, StudyRoomRepository studyRoomRepository, NotificationService notificationService) {
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.studyRoomRepository = studyRoomRepository;
        this.notificationService = notificationService;
    }

    public List<ReservationDTO> findAllOccupiedSeat() {
        return reservationRepository.findByOccupiedTrue().stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    public Reservation findReservationByPhone(String memberPhone) {
        Optional<Reservation> reservation = reservationRepository.findByMember_MemberPhoneAndOccupiedTrue(memberPhone);

        return reservation.orElse(null);
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
    public ReservationDTO findReservationByPhoneAndSeat(String memberPhone, int roomId) {
        Member member = findMemberByPhone(memberPhone);
        StudyRoom room = studyRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("유효하지 않은 좌석입니다."));

        //전화번호랑 좌석으로 예약 조회
        Optional<Reservation> reservation = reservationRepository.findByMember_MemberPhoneAndRoom_RoomId(memberPhone, roomId);

        //조회된 예약이 없는 경우 -> 예약
        if (reservation.isEmpty()) {
            Reservation newReservation = new Reservation(member, room);

            return new ReservationDTO(reservationRepository.save(newReservation));
        }

        return new ReservationDTO(reservation.get());
    }

    //예약
    @Transactional
    public ReservationDTO createReservation(int reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if (reservation.isPresent()) {
            Reservation foundReservation = reservation.get();

            foundReservation.setOccupied(true);

            String phone = "+82" + foundReservation.getMember().getMemberPhone();
            String roomName = foundReservation.getRoom().getRoomName();

            notificationService.sendMessage(phone, roomName + "번 좌석 예약되었습니다!");

            return new ReservationDTO(reservationRepository.save(foundReservation));
        } else {
            throw new IllegalArgumentException("Reservation with id " + reservationId+ " not found.");
        }
    }

    //퇴실
    @Transactional
    public ReservationDTO releaseReservation(int reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if (reservation.isPresent()) {
            Reservation foundReservation = reservation.get();

            foundReservation.setOccupied(false);

            String phone = "+82" + foundReservation.getMember().getMemberPhone();
            String roomName = foundReservation.getRoom().getRoomName();

            notificationService.sendMessage(phone, roomName + "번 좌석 퇴실되었습니다!");

            return new ReservationDTO(reservationRepository.save(foundReservation));
        } else {
            throw new IllegalArgumentException("Reservation with id " + reservationId + " not found.");
        }
    }
}
