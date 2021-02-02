package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface MemberMapper {
	public void joinMember(SignupRequestDTO member) throws SQLException;
	public void joinLawyer(MemberRequestDTO lawyer) throws SQLException;
	public MemberRequestDTO lawyer(String email) throws SQLException;
	public boolean signout(int uuid) throws SQLException;
	public boolean signoutLawyer(int uuid) throws SQLException;
	public int uuid(String email) throws SQLException;
	public void updateUser(MemberRequestDTO member)throws SQLException;
	public void updateLawyer(MemberRequestDTO lawyer) throws SQLException;
}
