package com.myproject.app;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.myproject.app.Classes.Course;
import com.myproject.app.Classes.Student;
import com.myproject.app.Classes.TimePeriod;



public class StudentManagementSystem 
{
    public static void main( String[] args )
    {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        String uri = "mongodb://localhost:27017";
        try{
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("StudentManagementDB").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Student> studentCollection = database.getCollection("Students",Student.class);
            MongoCollection<Course> courseCollection = database.getCollection("Courses", Course.class);
            // Inserting students
            Student ex1 = new Student("John Doe", "Business", 1, 1, false);
            studentCollection.insertOne(ex1);
            // Inserting courses:
            ArrayList<TimePeriod> p1 = new ArrayList<TimePeriod>();
            p1.add(new TimePeriod(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 45)));
            p1.add(new TimePeriod(DayOfWeek.THURSDAY, LocalTime.of(14, 30), LocalTime.of(15, 45)));

            ArrayList<TimePeriod> p2 = new ArrayList<TimePeriod>();
            p2.add(new TimePeriod(DayOfWeek.MONDAY, LocalTime.of(11, 00), LocalTime.of(12, 00)));
            p2.add(new TimePeriod(DayOfWeek.THURSDAY, LocalTime.of(16, 30), LocalTime.of(17, 45)));

            Course exampleCourse1 = new Course("Calculus 1", "MAT101", p1, 500.0, 25);
            Course exampleCourse2 = new Course("Politics 1", "POL101", p2, 300.0, 36);
            courseCollection.insertOne(exampleCourse1);
            courseCollection.insertOne(exampleCourse2);

            // Requesting courses:
            Iterable<Course> courseQuery = courseCollection.find(gte("availableSeats", 1));
            ArrayList<Course> availabeCourses = new ArrayList<Course>();
            for (Course i : courseQuery){
                availabeCourses.add(i);
                System.out.println(i);
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
