package com.ssafy.pjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages="com.ssafy.pjt.core.repository.mapper")
@EnableCaching
public class BasePjtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasePjtApplication.class, args);
	}

}
