package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MessageDTO {
	private int messageId;
	private String content;
	private int matchingId;
	private int uuid;
	private Date regDate;
	private String name;
}
