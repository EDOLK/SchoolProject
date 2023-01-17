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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.myproject.app.Classes.Course;
import com.myproject.app.Classes.Student;

public class ManagementSystem{
    private final CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private String uri;
    private MongoClient mc;
    private MongoDatabase db;
    private MongoCollection<Student> studentCollection;
    private MongoCollection<Course> courseCollection;

    public ManagementSystem(String uri) throws IllegalArgumentException{
        setUri(uri);
    }

    public ManagementSystem(){

    }

    public void setUri(String uri) throws IllegalArgumentException{
        mc = MongoClients.create(uri);
        db = mc.getDatabase("StudentManagementDB").withCodecRegistry(pojoCodecRegistry);
        this.uri = uri;
        getCollections();
    }

    private void getCollections(){
        studentCollection = db.getCollection("Students", Student.class);
        courseCollection = db.getCollection("Courses", Course.class);
    }

    public void addStudent(Student newStudent) throws Exception{
        studentCollection.insertOne(newStudent);
    }

}
