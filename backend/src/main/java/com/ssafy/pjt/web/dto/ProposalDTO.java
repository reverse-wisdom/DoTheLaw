package com.ssafy.pjt.web.dto;

import lombok.Data;

@Data
public class ProposalDTO {
/* opinion_id, uuid, content, agree */
	private int opinionId;
	private int uuid;
	private String content;
	private String agree;
}
