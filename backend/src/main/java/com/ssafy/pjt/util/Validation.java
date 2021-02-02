package com.ssafy.pjt.util;

import java.util.regex.Pattern;

public class Validation {
	
	static public boolean isPhone(String phone){
		return Pattern.matches("^[a-zA-Z0-9]*$", phone);
	}
	static public boolean isEmail(String email) {
		return Pattern.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email);
	}
	static public boolean isPassword(String password) {
		return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$", password);
	}
	
}
