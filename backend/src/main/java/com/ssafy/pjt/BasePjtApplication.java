package com.ssafy.pjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.ssafy.pjt.core.repository.mapper")
public class BasePjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasePjtApplication.class, args);
	}

}
