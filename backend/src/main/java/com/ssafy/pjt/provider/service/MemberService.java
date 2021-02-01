package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.repository.mapper.MemberMapper;
import com.ssafy.pjt.core.service.SignupUseCase;
import com.ssafy.pjt.web.dto.MemberDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

@Service
public class MemberService implements SignupUseCase{
	@Autowired
	private MemberMapper mapper;	
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public void joinMember(SignupRequestDTO member) throws SQLException {		  
		member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
		member.setRolecode(member.getRole().getCode());
		mapper.joinMember(member);
	}
	
	@Override
	public void joinLawyer(MemberDTO lawyer) throws SQLException{
		mapper.joinLawyer(lawyer);
	}
	
	@Override
	public boolean check(String email, String name) {
		Optional<Member> userOpt = memberRepository.findFirstByEmailOrName(email, name);		
		if(!userOpt.isPresent()) {
			return true;
		}
		return false;
	}
	@Override
	public boolean checkEmail(String email) {
		Optional<Member> userOpt = memberRepository.findByEmail(email);		
		if(userOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
