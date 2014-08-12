package com.kdubb.springdataneo4j.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertyConfig {
	private static final String DEFAULT_ENVIRONMENT = "LOCAL";
	private static final String ENVIRONMENT_SYS_PROPERTY = "env";

	private static final Logger LOG = LogManager.getLogger(PropertyConfig.class);

	public static void init() {
		String defaultEnv = System.getProperty(ENVIRONMENT_SYS_PROPERTY, DEFAULT_ENVIRONMENT);
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, defaultEnv);

		String env = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, defaultEnv);
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, env);

		// A warning to try to make sure it gets through
		LOG.warn("Starting with environment -> {" + env + "}");

		// TODO consider per-environment logging file
		// System.setProperty("logging.config", "classpath:logback-" + env +
		// ".xml");
	}

	public static String getEnvironment() {
		String env = System.getProperty(ENVIRONMENT_SYS_PROPERTY);

		if (StringUtils.isBlank(env))
			env = DEFAULT_ENVIRONMENT;

		return env;
	}

	@Bean
	public static PropertyPlaceholderConfigurer properties() {
		String env = getEnvironment();

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource("neo4j.properties"),
				new ClassPathResource("neo4j-" + env + ".properties") };
		ppc.setLocations(resources);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}
}
