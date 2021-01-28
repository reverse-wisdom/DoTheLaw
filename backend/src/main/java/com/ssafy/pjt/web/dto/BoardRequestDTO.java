package com.ssafy.pjt.web.dto;

import lombok.Data;

@Data
public class BoardRequestDTO {	
	private int board_id; 
    private int uuid;
    private String name;
    private String title;
    private String content;
    private int hit;  
    private String upload_date;
    private String category;
}
