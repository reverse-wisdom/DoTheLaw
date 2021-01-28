package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface SignUpMapper {
	public void joinMember(SignupRequestDTO member) throws SQLException;
}
