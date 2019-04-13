package com.phoenix.user;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import static com.mongodb.MongoClientOptions.builder;
import static com.mongodb.WriteConcern.ACKNOWLEDGED;
import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableMongoRepositories(basePackages = "com.phoenix.user")
public class MongoConfig extends AbstractMongoConfiguration {


  String mongoUri = "mongodb://localhost:27017";

  @Override
  protected String getDatabaseName() {
    return "phoenix_prep";
  }

  @Override
  public MongoClient mongoClient() {
	  System.out.println("mongoURI- " + mongoUri);
    return new MongoClient(new MongoClientURI(mongoUri, builder()
        .connectTimeout((int) SECONDS.toMillis(5))
        .socketTimeout((int) SECONDS.toMillis(10))
        .connectionsPerHost(100)
        .threadsAllowedToBlockForConnectionMultiplier(50)
        .readPreference(ReadPreference.primaryPreferred())
        .writeConcern(ACKNOWLEDGED)
    ));
  }
  @Override
  protected String getMappingBasePackage() {
      return "com.phoenix.user";
  }
}