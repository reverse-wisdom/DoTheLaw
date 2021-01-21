package com.example.demo.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.CommonResponse;
import com.example.demo.core.service.dto.MemberDTO;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.provider.security.JwtAuthToken;
import com.example.demo.provider.service.LoginService;
import com.example.demo.web.dto.LoginRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/login")

@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @ApiOperation(value = "로그인")
    @PostMapping
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