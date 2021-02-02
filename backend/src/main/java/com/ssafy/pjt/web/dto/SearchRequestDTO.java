package com.ssafy.pjt.web.dto;

import lombok.Data;

@Data
public class SearchRequestDTO {
	int searchId;
	String word;
	int hit;
}
