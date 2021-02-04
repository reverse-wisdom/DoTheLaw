package com.ssafy.pjt.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.SearchService;

@RequestMapping("/api/search")
@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	@GetMapping("/lawSearch")
	public ResponseEntity<?> searchWord(@RequestParam String query) {
		try {
			org.json.JSONObject result = searchService.searchByWordAndcountHit(query);
			return new ResponseEntity(result.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/lawService")
	public ResponseEntity<?> searchCode(@RequestParam(name = "ID") String id) {
		try {
			org.json.JSONObject result = searchService.searchByCode(id);
			return new ResponseEntity(result.toString(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/top")
	public ResponseEntity<?> top(){
		try {
			Map result = searchService.top();
			return new ResponseEntity(result, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dict")
	public ResponseEntity<?> getDict(){
		return new ResponseEntity(searchService.getDictAll(), HttpStatus.OK);
	}
}
