package com.ssafy.pjt.web.dto;

import lombok.Data;

@Data
public class MemberDTO {
	/* member - uuid, email, password, name, phone, role, join_date */
	/* lawyer - uuid, career, introduction, address, photo, dept, chck*/
	private int uuid;
	private String email;
	private String password;
	private String name;
	private String phone;
	private String role;
	private String joinDate;
	private String career;
	private String introduction;
	private String address;
	private String photo;
	private String depth;
	private String chck;
}