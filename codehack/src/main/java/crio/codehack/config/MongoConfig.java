package crio.codehack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

   
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017/codehackuserdb");
    }


    @Override
    protected String getDatabaseName() {
        return "codehackuserdb";
    }
}
