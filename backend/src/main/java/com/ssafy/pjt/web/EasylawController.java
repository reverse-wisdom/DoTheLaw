package com.ssafy.pjt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.EasylawService;

@RestController
@RequestMapping("/api/easylaw")
public class EasylawController {
	@Autowired
	EasylawService service;
	
	@GetMapping("/main")
	private ResponseEntity<?> memu() {
		List result = service.getMenu();
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
//	@GetMapping("/search")
//	private ResponseEntity<?> search(@RequestParam String keyword){
//		return new ResponseEntity(result, HttpStatus.OK);
//	}
	
	@GetMapping("/list")
	private ResponseEntity<?> search(@RequestParam int type){
		List result = service.crawlingEasyLawList(type);//service.searchEasylawByKeyword("type" + type + "#_")
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	private ResponseEntity<?> detail(@RequestParam int no){
		Map result = service.detail(no);
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
