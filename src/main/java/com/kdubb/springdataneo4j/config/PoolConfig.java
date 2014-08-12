package com.kdubb.springdataneo4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class PoolConfig {
	
	private int maxSize = 20;
	private int queueSize = 100;
	
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(maxSize);
		executor.setMaxPoolSize(maxSize);
		executor.setQueueCapacity(queueSize);
		
		return executor;
	}
}