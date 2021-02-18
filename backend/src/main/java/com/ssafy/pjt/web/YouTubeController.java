package com.ssafy.pjt.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.provider.service.YouTubeService;
import com.ssafy.pjt.web.dto.YouTubeDTO;

@RequestMapping("/api")
@RestController
public class YouTubeController {

	@Autowired
	private YouTubeService youTubeService;

	@GetMapping("/youtube")
	public List<YouTubeDTO> Index() throws SQLException, IOException {
		return youTubeService.get();
	}
}
