package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.repository.SignUpRepository;
import com.ssafy.pjt.core.service.SignUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.SignupRequsetDTO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class SignpuServiece implements SignUseCase{
	@Autowired
	private SqlSession sqlSession;	
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public void joinMember(SignupRequsetDTO member) throws SQLException {
//		Member memberDto = new Member();
//		memberDto.setEmail(member.getEmail());
//		memberDto.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
//		memberDto.setPhone(member.getPhone());
//		memberDto.setName(member.getName());
//		memberDto.setRole(member.getRole().getCode());
		  
		member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
		member.setRolecode(member.getRole().getCode());
		sqlSession.getMapper(SignUpRepository.class).joinMember(member);
	}
	@Override
	public boolean check(String email, String name) {
		Optional<Member> userOpt = memberRepository.findByEmailOrName(email, name);		
		if(!userOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
