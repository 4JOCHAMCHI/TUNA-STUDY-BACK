package com.team4chamchi.tunastudy.member.repository;

import com.team4chamchi.tunastudy.member.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
