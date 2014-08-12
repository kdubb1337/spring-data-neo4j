package com.kdubb.springdataneo4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * 
 * @author mohit
 * @file : Neo4JConfig.java
 * @description : Class for configuring neo4j database
 */
@Configuration
@EnableNeo4jRepositories({"com.kdubb.springdataneo4j.repository"})
public class Neo4JConfig extends Neo4jConfiguration{
	
	public Neo4JConfig() {
		setBasePackage("com.kdubb.springdataneo4j");
	}
}