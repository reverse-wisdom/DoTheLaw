package com.ssafy.pjt.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.EmailService;
import com.ssafy.pjt.provider.service.SearchService;

@RequestMapping("/api/email")
@RestController
public class EmailController {

	@Autowired
	EmailService emailhService;

	@GetMapping("/check")
	public ResponseEntity<String> searchEmail(@RequestParam String email) {
		try {
			if(emailhService.searchEmail(email)) {
				return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("FAIL", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
		}
	}
	

}
