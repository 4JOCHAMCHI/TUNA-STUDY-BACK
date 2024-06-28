package com.team4chamchi.tunastudy.reservation.aggregate;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import com.team4chamchi.tunastudy.studyroom.aggregate.StudyRoom;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "occupied")
    private Boolean occupied;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private StudyRoom room;

    public Reservation(Member member, StudyRoom room) {
        this.occupied = true;
        this.member = member;
        this.room = room;
    }
}