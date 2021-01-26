package com.ssafy.pjt.web;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.ssafy.pjt.provider.service.RSSservice;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rss")
@CrossOrigin(origins = { "http://localhost:8080" })
public class RSSController {
	
	@Autowired
	private RSSservice service; 
	
	@ApiOperation(value = "뉴스파싱")
	@RequestMapping("/news")
	private ResponseEntity newsParsing() throws SAXException, IOException, ParserConfigurationException {
		List result = service.parsingNews();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
