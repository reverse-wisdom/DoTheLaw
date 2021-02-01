package com.ssafy.pjt.core.service;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface SignupUseCase {
	
	public void joinMember(SignupRequestDTO member) throws Exception;
	public void joinLawyer(MemberRequestDTO lawyer) throws SQLException;
	public boolean check(String email, String name);
	public boolean checkEmail(String email);
}
