package com.mka;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 
@EnableMongoRepositories
@Slf4j
public class MongoConfiguration {
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	@Value("${com.mka.iot.database.embedded}")
	private boolean useEmbedded;
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		if(useEmbedded) {
			log.info("############################################################");
			log.info("#           Embedded mongo db in use                       #");
			log.info("#                                                          #");
			log.info("#           All data is lost in restart!                   #");
			log.info("############################################################");
			EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		    mongo.setBindIp(host);
		    MongoClient mongoClient = mongo.getObject();
		    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, database);
		    return mongoTemplate; 
		} else {
			log.info("############################################################");
			log.info("#           Using Real Mongo db                            #");
			log.info("############################################################");
			return new MongoTemplate(new MongoClient(host), database);
		}
		
	}
 
}