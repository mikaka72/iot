package com.mka;

import com.mongodb.MongoClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 
@EnableMongoRepositories
public class MongoConfiguration {
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Value("${com.mka.iot.database.embedded}")
	private boolean useEmbedded;
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		
		return new MongoTemplate(new MongoClient(host), database);
		
	}
 
}