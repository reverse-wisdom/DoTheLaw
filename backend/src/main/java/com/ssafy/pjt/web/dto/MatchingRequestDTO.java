package com.ssafy.pjt.web.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MatchingRequestDTO {
 /* matching_id, uuid, lawyer_uuid, create_date, content, state, reservation_date */
	private int matchingId;
	private int uuid;
	private int lawyerUuid;
	private Date createDate;
	private String content;
	private String state;
	private Date reservationDate;
	 @ApiModelProperty(required = false, hidden = true)
	private String name;
	private String remarks;
	private String title;
	private String category;
}
