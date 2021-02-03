package com.ssafy.pjt.web.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProposalRequestDTO {
	@ApiModelProperty(required = false)
	private int opinionId;
	private int controversyId; 
    private int uuid;
    private String content;
    private char agree;
    @ApiModelProperty(required = false, hidden = true)
    private String name;
}
