package com.epam.training.config;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.epam.training.repository")
public class MongoDBConfig extends AbstractMongoConfiguration {
    @Override
    public String getDatabaseName() {
        return "exam_results";
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        ServerAddress address = new ServerAddress("localhost", 27017);
        MongoClientOptions options = new MongoClientOptions.Builder().build();
        MongoClient client = new MongoClient(address, options);
        return client;
    }
}
