package com.team4chamchi.tunastudy.studyroom.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team4chamchi.tunastudy.reservation.aggregate.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "studyroom")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "room_name")
    private String roomName;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservations;
}
