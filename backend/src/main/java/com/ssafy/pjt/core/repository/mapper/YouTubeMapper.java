package com.ssafy.pjt.core.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.pjt.web.dto.YouTubeDTO;

public interface YouTubeMapper {
	List<YouTubeDTO> getTodayYouTube() throws SQLException;
	void			 setTodayYouTube(YouTubeDTO data) throws SQLException;
}
