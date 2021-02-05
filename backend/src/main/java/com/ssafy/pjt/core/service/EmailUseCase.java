package com.ssafy.pjt.core.service;

import java.util.List;

import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.web.dto.BoardRequestDTO;


public interface EmailUseCase {
	public boolean searchEmail(String email) throws Exception;
}
