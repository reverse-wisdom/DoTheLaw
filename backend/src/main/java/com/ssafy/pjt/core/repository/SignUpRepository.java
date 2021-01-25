package com.ssafy.pjt.core.repository;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.SignupRequsetDTO;

public interface SignUpRepository {
	public void joinMember(SignupRequsetDTO member) throws SQLException;
}
