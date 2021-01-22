package com.ssafy.pjt.core.service;

import com.ssafy.pjt.core.security.AuthToken;
import com.ssafy.pjt.core.service.dto.MemberDTO;

import java.util.Optional;

public interface LoginUseCase {
    Optional<MemberDTO> login(String id, String password);
    AuthToken createAuthToken(MemberDTO memberDTO);
}
