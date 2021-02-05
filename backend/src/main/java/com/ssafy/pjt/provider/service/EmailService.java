package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.repository.mapper.BoardMapper;
import com.ssafy.pjt.core.repository.mapper.MemberMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.BoardUseCase;
import com.ssafy.pjt.core.service.EmailUseCase;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.util.EmailUtils;
import com.ssafy.pjt.web.dto.BoardRequestDTO;

@Service
public class EmailService implements EmailUseCase {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	private MemberMapper memberMapper; 

	@Override
	public boolean searchEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Optional<MemberDTO> member = memberRepository.findMemberByEmail(email);
		String pass = EmailUtils.getRamdomPassword(10);
		if(member.isPresent()) {		
			memberMapper.passwordUpdate(Integer.parseInt(member.get().getUuid()), new BCryptPasswordEncoder().encode(pass));
			EmailUtils.gmailSend(email,pass);
			return true;
		}
		
		return false;
	}



}
