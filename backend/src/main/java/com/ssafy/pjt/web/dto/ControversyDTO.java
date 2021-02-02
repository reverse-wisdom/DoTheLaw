package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ControversyDTO {
	private int controversyId;
	private int uuid;
	private String name;
	private Date startDate;
	private Date endDate;
	private String title;
	private String content;
	private int hit;
}
