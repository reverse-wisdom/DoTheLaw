package com.ssafy.pjt.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.SignupService;
import com.ssafy.pjt.web.dto.SignupRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
//@CrossOrigin(origins = { "http://localhost:8080" })
@RequiredArgsConstructor
public class SignupControllor {

	@Autowired
	private SignupService service;
	
	
    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
	private ResponseEntity<String> join(@Valid @RequestBody SignupRequestDTO signupRequsetDTO) {
    	
    	if(service.check(signupRequsetDTO.getEmail(), signupRequsetDTO.getName())) {
    		try {			   			
    			service.joinMember(signupRequsetDTO);
    			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "이메일 중복체크")
    @GetMapping("/check")
	private ResponseEntity<String> checkEmail(@RequestParam(required = true) final String email) {    	
    	if(service.checkEmail(email)) {   
    		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
	}
    //회원 정보 조회
    @ApiOperation(value = "회원 정보 조회")
    @GetMapping("/lookup")
	private ResponseEntity<String> lookup(@RequestParam(required = true) final String email) {    	
    	if(service.checkEmail(email)) {   
    		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	}		
		return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
	}
    //회원 탈퇴
    
    //회원 정보 수정
    
}
