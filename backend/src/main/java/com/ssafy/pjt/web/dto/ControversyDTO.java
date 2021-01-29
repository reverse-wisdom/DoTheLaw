package com.ssafy.pjt.web.dto;

import lombok.Data;

@Data
public class ControversyDTO {
	private int controversyId;
	private int uuid;
	private String name;
	private String startDate;
	private String endDate;
	private String title;
	private String content;
	private int hit;
}
