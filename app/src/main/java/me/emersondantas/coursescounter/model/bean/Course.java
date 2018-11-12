package me.emersondantas.coursescounter.model.bean;

import me.emersondantas.coursescounter.model.dao.BaseEntity;

/**
 * Class representing a course.
 * @author Emerson Dantas
 */
public class Course implements BaseEntity{
    private long id;
    private String name;
    private int numOfLessons;
    private int hoursOfTheCourse;
    private int currentLesson;
    private String linkForTheCourse;

    public Course(int id, String name, int numOfLessons, int hoursOfTheCourse, int currentLesson, String linkForTheCourse){
        this.id = id;
        this.name = name;
        this.numOfLessons = numOfLessons;
        this.hoursOfTheCourse = hoursOfTheCourse;
        this.currentLesson = currentLesson;
        this.linkForTheCourse = linkForTheCourse;
    }
    
    public Course(String name, int numOfLessons, int hoursOfTheCourse, int currentLesson, String linkForTheCourse) {
        this(0, name, numOfLessons, hoursOfTheCourse, currentLesson, linkForTheCourse);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfLessons() {
        return numOfLessons;
    }

    public void setNumOfLessons(int numOfLessons) {
        this.numOfLessons = numOfLessons;
    }

    public int getHoursOfTheCourse() {
        return hoursOfTheCourse;
    }

    public void setHoursOfTheCourse(int hoursOfTheCourse) {
        this.hoursOfTheCourse = hoursOfTheCourse;
    }

    public int getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(int currentLesson) {
        this.currentLesson = currentLesson;
    }

    public String getLinkForTheCourse() {
        return linkForTheCourse;
    }

    public void setLinkForTheCourse(String linkForTheCourse) {
        this.linkForTheCourse = linkForTheCourse;
    }

    @Override
    public long getId() {
        return this.id;
    }
}
