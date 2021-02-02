package com.ssafy.pjt.web.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentRequestDTO {
	@ApiModelProperty(required = false)
	private int commentId;
	private int boardId; 
    private int uuid;
    private String content;
    @ApiModelProperty(required = false, hidden = true)
    private String name;
}
