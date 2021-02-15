package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class YouTubeDTO {
	private String id;
	private String title;
	private String thumbnail;
	private Date publishedAt;
	private String channelId;
	private String description;
}
