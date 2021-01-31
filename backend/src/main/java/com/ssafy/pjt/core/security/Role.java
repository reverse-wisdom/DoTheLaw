package com.ssafy.pjt.core.security;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
	UNKNOWN("UNKNOWN",  "알수없는 권한"),
	USER("ROLE_USER",  "사용자권한"),
	LAWYER("ROLE_LAWYER", "변호사권한"), 
	ADMIN("ROLE_ADMIN",  "관리자권한");

	final private String code;
	final private String description;

	Role(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static Role of(String code) {
		return Arrays.stream(Role.values())
				.filter(r -> r.getCode().equals(code))
				.findAny()
				.orElse(UNKNOWN);
	}
	/** target이 해당 권한을 가지고 있는지 체크 */
	public static boolean hasPermission(Role origin, Role target) {
		return origin.ordinal() <= target.ordinal();
	}

}