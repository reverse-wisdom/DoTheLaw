package com.ssafy.pjt.core.service;

import com.ssafy.pjt.web.dto.SignupRequestDTO;

public interface SignupUseCase {
	
	public void joinMember(SignupRequestDTO member) throws Exception;
	public boolean check(String email, String name);
	public boolean checkEmail(String email);
}
