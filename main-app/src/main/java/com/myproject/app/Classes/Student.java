package com.myproject.app.Classes;

import java.util.ArrayList;

public class Student{
    private String name;
    private String major;
    private int year;
    private int semester;
    private boolean graduate;
    private ArrayList<Course> courseRegistry;

    public Student(){
        courseRegistry = new ArrayList<Course>();
    }

    public Student(String name, String major, int year, int semester, boolean graduate){
        this.name = name;
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.graduate = graduate;
        this.courseRegistry = new ArrayList<Course>();
    }
    
    public void addCourse(Course c) throws Exception{
        if (!courseRegistry.contains(c)){
            for (int i = 0; i < courseRegistry.size(); i++){
                for (int l = 0; l < courseRegistry.get(i).getSchedule().size(); l++){
                    for (int k = 0; k < c.getSchedule().size(); k++){
                        if(c.getSchedule().get(k).overlap(courseRegistry.get(i).getSchedule().get(l))){
                            throw new Exception("Error: course overlaps with pre-existing courses");
                        }
                    }
                }
            }
            courseRegistry.add(c);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean isGraduate() {
        return graduate;
    }

    public void setGraduate(boolean graduate) {
        this.graduate = graduate;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", major=" + major + ", year=" + year + ", semester=" + semester
                + ", graduate=" + graduate + ", courseRegistry=" + courseRegistry + "]";
    }


    
}