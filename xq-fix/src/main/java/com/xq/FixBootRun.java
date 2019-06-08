package com.xq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xq.mapper")
public class FixBootRun {
	public static void main(String[] args) {
		SpringApplication.run(FixBootRun.class, args);
	}
}
