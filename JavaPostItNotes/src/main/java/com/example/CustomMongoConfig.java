package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;


public class CustomMongoConfig extends AbstractMongoClientConfiguration {

	private static String dynamicDatabaseName;

	// Instance method to get the database name
	@Override
	protected String getDatabaseName() {
		return dynamicDatabaseName != null ? dynamicDatabaseName : "demo";
	}

	@Bean
	@Override
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	public static void setDynamicDatabaseName(String dbName) {
		dynamicDatabaseName = dbName;
	}

	public static String getDynamicDatabaseName() {
		return dynamicDatabaseName;
	}
}

