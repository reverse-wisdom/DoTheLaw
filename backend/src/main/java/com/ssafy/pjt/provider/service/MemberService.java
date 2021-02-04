package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.repository.MemberRepository;
import com.ssafy.pjt.core.repository.mapper.MemberMapper;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.MemberUseCase;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

@Service
public class MemberService implements MemberUseCase {
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void joinMember(SignupRequestDTO member) throws SQLException {
		member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
		// member.setRolecode(member.getRole().getCode());
		mapper.joinMember(member);
	}

	@Override
	public void joinLawyer(MemberRequestDTO lawyer) throws SQLException {
		lawyer.setPassword(new BCryptPasswordEncoder().encode(lawyer.getPassword()));
		mapper.joinLawyer(lawyer);
	}

	@Override
	public boolean check(String email, String name) {
		Optional<Member> userOpt = memberRepository.findFirstByEmailOrName(email, name);
		if (!userOpt.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkEmail(String email) {
		Optional<Member> userOpt = memberRepository.findByEmail(email);
		if (userOpt.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkName(String name) {
		Optional<Member> userOpt = memberRepository.findByName(name);
		if (userOpt.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public MemberRequestDTO lawyer(String email) throws SQLException {
		return mapper.lawyer(email);
	}

	@Override
	public boolean signout(String role, String email) throws SQLException {
		int uuid = mapper.uuid(email);
		if (role.equals("ROLE_USER")) {
			if (mapper.signout(uuid)) {
				return true;
			}
		} else {
			if (mapper.signoutLawyer(uuid) && mapper.signout(uuid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateUser(MemberRequestDTO member) throws SQLException {
		member.setUuid(mapper.uuid(member.getEmail()));
		mapper.updateUser(member);
	}

	@Override
	public void updateLawyer(MemberRequestDTO lawyer) throws SQLException {
		lawyer.setUuid(mapper.uuid(lawyer.getEmail()));
		mapper.updateUser(lawyer);
		mapper.updateLawyer(lawyer);
	}

	public MemberRequestDTO getMember(int uuid) throws SQLException {
		return mapper.selectMemberByUUID(uuid);
	}

}
