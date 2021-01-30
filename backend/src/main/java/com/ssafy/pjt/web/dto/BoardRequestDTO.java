package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardRequestDTO {	
	private int boardId; 
    private int uuid;
    private String name;
    private String title;
    private String content;
    private int hit;  
    private Date uploadDate;
    private String category;
}
