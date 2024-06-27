package com.team4chamchi.tunastudy.studyroom.respository;

import com.team4chamchi.tunastudy.studyroom.aggregate.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
}
