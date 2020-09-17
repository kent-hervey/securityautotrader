package com.autotrader.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

    @Bean
    public DynamoDBMapper getDynamoDBMapper(AmazonDynamoDB client) {
        return new DynamoDBMapper(client);
    }

}
