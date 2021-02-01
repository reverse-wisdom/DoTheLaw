package com.ssafy.pjt.core.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.MemberDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface MemberUseCase {
	
	public void joinMember(SignupRequestDTO member) throws Exception;
	void joinLawyer(MemberDTO lawyer) throws SQLException;
	public boolean check(String email, String name);
	public boolean checkEmail(String email);
	public boolean checkName(String name);
	public boolean checkRoleAndId(Role role, int uuid, String email);
}
