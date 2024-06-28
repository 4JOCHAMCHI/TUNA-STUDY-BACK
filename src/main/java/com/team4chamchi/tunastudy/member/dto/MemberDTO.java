package com.team4chamchi.tunastudy.member.dto;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int memberId;
    private String memberPhone;

    public MemberDTO(Member member) {
        this.memberId = member.getMemberId();
        this.memberPhone =  member.getMemberPhone();
    }
}
