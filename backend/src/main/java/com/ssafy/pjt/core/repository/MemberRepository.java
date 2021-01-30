package com.ssafy.pjt.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt.core.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailOrName(String email,String name);
	Optional<Member> findFirstByEmailOrName(String email, String name);
}
