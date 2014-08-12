package com.kdubb.springdataneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.kdubb.springdataneo4j.config.PropertyConfig;

@ComponentScan
@Configuration
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		PropertyConfig.init();
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		PropertyConfig.init();
		return application.sources(Application.class);
	}
}