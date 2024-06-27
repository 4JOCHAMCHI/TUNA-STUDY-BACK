package com.team4chamchi.tunastudy.reservation.dto;

import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private int reservationId;
    private Boolean usage;
    private int memberId;
    private String memberPhone;
    private int roomId;
    private String roomName;

    public ReservationDTO(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.usage = reservation.getUsage();
        this.memberId = reservation.getMember().getMemberId();
        this.memberPhone = reservation.getMember().getMemberPhone();
        this.roomId = reservation.getRoom().getRoomId();
        this.roomName = reservation.getRoom().getRoomName();
    }
}
