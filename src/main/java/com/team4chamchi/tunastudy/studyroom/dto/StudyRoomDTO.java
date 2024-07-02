package com.team4chamchi.tunastudy.studyroom.dto;

import com.team4chamchi.tunastudy.studyroom.aggregate.StudyRoom;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudyRoomDTO {
    private int roomId;
    private String roomName;

    public StudyRoomDTO(StudyRoom studyRoom) {
        this.roomId = studyRoom.getRoomId();
        this.roomName = studyRoom.getRoomName();
    }
}
