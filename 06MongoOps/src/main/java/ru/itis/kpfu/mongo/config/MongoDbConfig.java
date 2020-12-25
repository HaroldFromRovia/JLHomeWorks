package ru.itis.kpfu.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.itis.kpfu.mongo.models.Game;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
@ComponentScan("ru.itis.kpfu.mongo")
public class MongoDbConfig {

    private final Environment environment;

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), environment.getProperty("mongo.db.name"));
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(environment.getProperty("mongo.db.name"));
    }

}
