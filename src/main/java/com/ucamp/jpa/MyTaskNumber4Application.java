package com.ucamp.jpa;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyTaskNumber4Application {

	public static void main(String[] args) {
		SpringApplication.run(MyTaskNumber4Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// private 필드를 찾아올 수 있게 해줌
//		modelMapper.getConfiguration()
//				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
//				.setFieldMatchingEnabled(true);
		return modelMapper;
	}
}
