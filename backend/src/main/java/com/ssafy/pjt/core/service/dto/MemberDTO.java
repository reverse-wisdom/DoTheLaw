package com.ssafy.pjt.core.service.dto;

import com.ssafy.pjt.core.entity.Member;
import com.ssafy.pjt.core.security.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String uuid;
    private String email;
    private String password;
    private String phone;
    private String name;  
    private Role role;
    private String joinDate;
    private String image;
    public static MemberDTO of(Member member) {
        return MemberDTO.builder()
                .uuid(String.valueOf(member.getUuid()))
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .role(Role.of(member.getRole()))
                .joinDate(member.getJoinDate())
                .image(member.getImage())
                .build();
    }
	public MemberDTO(String email, String password, String phone, String name, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.role = role;
	}
    
}