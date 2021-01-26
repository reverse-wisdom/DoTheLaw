package com.ssafy.pjt.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.core.CommonResponse;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.exception.LoginFailedException;
import com.ssafy.pjt.provider.security.JwtAuthToken;
import com.ssafy.pjt.provider.service.LoginService;
import com.ssafy.pjt.web.dto.LoginRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<MemberDTO> optionalMemberDTO = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if (optionalMemberDTO.isPresent()) {

            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(optionalMemberDTO.get());

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();

        } else {
            throw new LoginFailedException();
        }
    }
}