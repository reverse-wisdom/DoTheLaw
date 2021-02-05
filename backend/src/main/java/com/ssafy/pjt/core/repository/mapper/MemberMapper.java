package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface MemberMapper {
	public void joinMember(SignupRequestDTO member) throws SQLException;
	public void joinLawyer(MemberRequestDTO lawyer) throws SQLException;
	public MemberRequestDTO lawyer(String email) throws SQLException;
	public List<MemberRequestDTO> lawyerAll() throws SQLException;
	public boolean signout(int uuid) throws SQLException;
	public boolean signoutLawyer(int uuid) throws SQLException;
	public int uuid(String email) throws SQLException;
	public void updateUser(MemberRequestDTO member)throws SQLException;
	public void updateLawyer(MemberRequestDTO lawyer) throws SQLException;
	public MemberRequestDTO selectMemberByUUID(int uuid) throws SQLException;
	public void passwordUpdate(int uuid, String password) throws SQLException;
}
