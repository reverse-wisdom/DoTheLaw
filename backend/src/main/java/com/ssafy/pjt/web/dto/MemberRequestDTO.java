package com.ssafy.pjt.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberRequestDTO {
	/* member - uuid, email, password, name, phone, role, join_date, image */
	/* lawyer - uuid, career, introduction, address, dept, chck*/
	private int uuid;
	private String email;
	private String password;
	private String name;
	private String phone;
	private String role;
	private Date joinDate;
	private String image;
	private String career;
	private String introduction;
	private String address;
	private String dept;
	private String chck;
	private String bailiwick;
}