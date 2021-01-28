package com.ssafy.pjt.web;

import java.sql.SQLException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.core.CommonResponse;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.core.service.dto.MemberDTO;
import com.ssafy.pjt.exception.LoginFailedException;
import com.ssafy.pjt.provider.security.JwtAuthToken;
import com.ssafy.pjt.provider.service.LoginService;
import com.ssafy.pjt.provider.service.SignupService;
import com.ssafy.pjt.web.dto.LoginRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;
import com.ssafy.pjt.web.dto.SocialSignupRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
	@Autowired
	private SignupService signupService;
	
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
    
    @ApiOperation(value = "소셜로그인")
    @PostMapping("/social")
    public CommonResponse SocialLogin(@Valid @RequestBody SocialSignupRequestDTO socialsignupRequsetDTO) {
    	//소셜 아이디 구분하기
    	socialsignupRequsetDTO.setEmail(socialsignupRequsetDTO.getType().trim()+"_"+socialsignupRequsetDTO.getEmail());
    	MemberDTO memberDTO = MemberDTO.builder()
                .name(socialsignupRequsetDTO.getName())
                .email(socialsignupRequsetDTO.getEmail())
                .role(socialsignupRequsetDTO.getRole())
                .build();
        if (signupService.checkEmail(socialsignupRequsetDTO.getEmail())) { //DB에 있을 때
            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(memberDTO);

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();

        } else {// DB에 없을때
        	//데이터 베이스에 저장용
        	SignupRequestDTO signupRequsetDTO = new SignupRequestDTO();
        	signupRequsetDTO.setEmail(socialsignupRequsetDTO.getEmail());
        	signupRequsetDTO.setPassword(socialsignupRequsetDTO.getId());
        	signupRequsetDTO.setName(socialsignupRequsetDTO.getName());
        	signupRequsetDTO.setRole(socialsignupRequsetDTO.getRole());
        	
        	try {
				signupService.joinMember(signupRequsetDTO);
			} catch (SQLException e) {
				throw new LoginFailedException();
			}
        	
        	JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(memberDTO);
        	
            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();
        }
    }
    
}