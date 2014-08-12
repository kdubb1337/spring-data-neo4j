package com.kdubb.springdataneo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@Configuration
@AutoConfigureAfter({ Neo4JConfig.class })
public class GraphDatabaseConfig {

	@Value("${neo4j.url}")
	private String neo4jUrl;
	
	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new SpringRestGraphDatabase(neo4jUrl);
	}
}
