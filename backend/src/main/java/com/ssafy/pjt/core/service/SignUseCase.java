package com.ssafy.pjt.core.service;

import java.sql.SQLException;

import com.ssafy.pjt.web.dto.SignupRequsetDTO;

public interface SignUseCase {
	
	public void joinMember(SignupRequsetDTO member) throws Exception;
	public boolean check(String email, String name);
}
