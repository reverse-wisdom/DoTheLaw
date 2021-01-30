package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MatchingDTO {
 /* matching_id, uuid, lawyer_uuid, create_date, content, state, reservation_date */
	private int matchingId;
	private int uuid;
	private int lawyer_uuid;
	private Date createDate;
	private String content;
	private String state;
	private Date reservationDate;
}
