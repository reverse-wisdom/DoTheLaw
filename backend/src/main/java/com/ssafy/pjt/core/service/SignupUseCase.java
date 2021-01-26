package com.ssafy.pjt.core.service;

import com.ssafy.pjt.web.dto.SignupRequsetDTO;

public interface SignupUseCase {
	
	public void joinMember(SignupRequsetDTO member) throws Exception;
	public boolean check(String email, String name);
	public boolean checkEmail(String email);
}
