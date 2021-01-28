package com.ssafy.pjt.core.repository;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface SignUpRepository {
	public void joinMember(SignupRequestDTO member) throws SQLException;
}
