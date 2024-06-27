package com.team4chamchi.tunastudy.reservation.service;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import com.team4chamchi.tunastudy.member.repository.MemberRepository;
import com.team4chamchi.tunastudy.reservation.dto.ReservationDTO;
import com.team4chamchi.tunastudy.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private MemberRepository memberRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public Optional<ReservationDTO> getUserByPhone(String memberPhone) {
        Optional<ReservationDTO> reservation = reservationRepository.findByMember_MemberPhone(memberPhone).stream()
                .findFirst()
                .map(ReservationDTO::new);

        if (reservation.isEmpty()) {
            Member member = new Member(memberPhone);
            memberRepository.save(member);
        }
    }

    //조회해서 멤버가 없으면 생성
    //조회해서 내역이 없으면 예약
    //조회해서 내역이 있으면 퇴실
}
