package com.ssafy.pjt.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.SearchService;

@RequestMapping("/api/lawSearch")
@RestController
public class SearchController {

	@Autowired
	SearchService searchService;

	@GetMapping()
	public ResponseEntity<?> searchWord(@RequestParam String query) {
		try {
			org.json.JSONObject result = searchService.searchByWord(query);
			return new ResponseEntity(result.toString(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
