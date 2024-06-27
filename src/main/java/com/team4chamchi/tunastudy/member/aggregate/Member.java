package com.team4chamchi.tunastudy.member.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(name = "member_phone")
    private String memberPhone;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservations;
}