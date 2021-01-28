package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.repository.SignUpRepository;
import com.ssafy.pjt.core.service.SignupUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class SignupService implements SignupUseCase{
	@Autowired
	private SqlSession sqlSession;	
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public void joinMember(SignupRequestDTO member) throws SQLException {		  
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
	@Override
	public boolean checkEmail(String email) {
		Optional<Member> userOpt = memberRepository.findByEmail(email);		
		if(userOpt.isPresent()) {
			return true;
		}
		return false;
	}
}
