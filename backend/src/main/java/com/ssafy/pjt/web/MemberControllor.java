package com.ssafy.pjt.web;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.security.Role;
import com.ssafy.pjt.provider.service.LoginService;
import com.ssafy.pjt.provider.service.MemberService;
import com.ssafy.pjt.web.dto.BoardRequestDTO;
import com.ssafy.pjt.web.dto.MemberRequestDTO;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class MemberControllor {

	@Autowired
	private MemberService service;
	@Autowired
	private LoginService loginService;
	
    @ApiOperation(value = "일반회원 회원가입")
    @PostMapping("/signup")
	private ResponseEntity<String> join(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
    	
    	if(service.check(signupRequestDTO.getEmail(), signupRequestDTO.getName())) {
    		try {			   			
    			service.joinMember(signupRequestDTO);
    			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "변호사 회원가입")
    @PostMapping("/signup/lawyer")
	private ResponseEntity<String> joinLawyer(@Valid @RequestBody MemberRequestDTO memberDTO) {
    	memberDTO.setRole("ROLE_LAWYER");
    	System.out.println(memberDTO);
    	if(service.check(memberDTO.getEmail(), memberDTO.getName())) {
    		try {			   			
    			service.joinLawyer(memberDTO);
    			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else
    		return new ResponseEntity<>("중복된 이메일 또는 닉네임이 존재합니다", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
	}
    
    @ApiOperation(value = "이메일 중복체크")
    @GetMapping("/check/email")
	private ResponseEntity<String> checkEmail(@RequestParam(required = true) final String email) {    	
    	if(!service.checkEmail(email)) {   
    		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
	}
    
    @ApiOperation(value = "이름 중복체크")
    @GetMapping("/check/name")
	private ResponseEntity<String> checkName(@RequestParam(required = true) final String name) {    	
    	if(!service.checkName(name)) {   
    		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
	}
    
    //회원 정보 조회
    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/lookup")
	private ResponseEntity<Member> lookup(@RequestParam(required = true) final String email) {    	
    	Member member;
    	if(!service.checkEmail(email)) {
    		member = loginService.user(email);
    		return new ResponseEntity<>(member, HttpStatus.OK);
    	}		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
    
    //변호사 회원 조회
    

    //회원 탈퇴 미완성
	@ApiOperation(value = "회원탈퇴(아직 안됨)")
	@DeleteMapping("/delete")
	private ResponseEntity<String> signout(@RequestParam(required = true) final int uuid
										   , @RequestParam(required = true) final Role role, @RequestParam(required = true) final String email) {
		
		
		if(loginService.user(email).getUuid().equals(String.valueOf(uuid)) ) {
			
		}
		return null;
	}

    //회원 정보 수정
    
}
