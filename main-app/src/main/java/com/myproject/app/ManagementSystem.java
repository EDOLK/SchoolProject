package com.myproject.app;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
// import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ManagementSystem{
    private final CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private String uri;
    private MongoClient mc;
    private MongoDatabase db;

    public ManagementSystem(String uri){
        try{
            mc = MongoClients.create(uri);
            db = mc.getDatabase("StudentManagementDB").withCodecRegistry(pojoCodecRegistry);
            this.uri = uri;
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void setUri(String uri){
        try{
            mc = MongoClients.create(uri);
            db = mc.getDatabase("StudentManagementDB").withCodecRegistry(pojoCodecRegistry);
            this.uri = uri;
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
