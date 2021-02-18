package com.ssafy.pjt.web.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BoardRequestDTO {
	private int boardId; 
    private int uuid;
    private String name;
    private String title;
    private String content;
    @ApiModelProperty(required = false, hidden = true)
    private int hit;
    @ApiModelProperty(required = false, hidden = true)
    private Date uploadDate;
    private String category;
}
