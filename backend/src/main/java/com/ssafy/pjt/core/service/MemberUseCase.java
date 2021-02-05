package com.ssafy.pjt.core.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface MemberUseCase {
	
	public void joinMember(SignupRequestDTO member) throws Exception;
	void joinLawyer(MemberRequestDTO lawyer) throws SQLException;
	public boolean check(String email, String name);
	public boolean checkEmail(String email);
	public boolean checkName(String name);
	public MemberRequestDTO lawyer(String email) throws SQLException;
	public boolean signout(String role, String eamil) throws SQLException;
	public void updateUser(MemberRequestDTO member)throws SQLException;
	public void updateLawyer(MemberRequestDTO lawyer) throws SQLException;
	public List<MemberRequestDTO> lawyerAll() throws SQLException;
	public boolean passwordUpdate(int uuid, String password) throws SQLException;
}
