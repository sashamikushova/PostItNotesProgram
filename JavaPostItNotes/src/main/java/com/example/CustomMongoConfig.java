package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class CustomMongoConfig extends AbstractMongoClientConfiguration {

	public static String dynamicCollectionName = "user";

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	public static void setDynamicCollectionName(String collectionName) {
		dynamicCollectionName = collectionName;
	}

	@Bean
	@Override
	public MongoClient mongoClient() {
		return MongoClients.create(mongoUri);
	}

	@Bean
	@Override
	protected String getDatabaseName() {
		return "demo"; // use the dynamic collection name as the database name
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoClient(), getDatabaseName());
	}

	@Bean
	public SimpleMongoClientDatabaseFactory mongoDbFactory() {
		return new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
	}
}
