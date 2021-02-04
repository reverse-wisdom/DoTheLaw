package com.ssafy.pjt.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadPathConfiguration {

	@Bean(name = "uploadPath")
	public String uploadPath() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win"))
			return "c:" + File.separator + "PJT-data";
		else // AWS용
			return "/volumes/data";
	}

	@Autowired
	@Bean(name = "lawyerImagePath")
	public String lawyerImagePath(String uploadPath) {
		return uploadPath + File.separator + "lawyer";
	}

}
