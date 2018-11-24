package me.emersondantas.coursescounter.model.bean;

/**
 * Class representing a course.
 * @author Emerson Dantas
 */
public class Course{
    private int id;
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

    public void setHoursOfTheCourse(int hoursOfTheCourse) { this.hoursOfTheCourse = hoursOfTheCourse; }

    public int getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(int currentLesson) {
        this.currentLesson = currentLesson;
    }

    public String getLinkForTheCourse() {
        return linkForTheCourse;
    }

    public void setLinkForTheCourse(String linkForTheCourse) { this.linkForTheCourse = linkForTheCourse; }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return name;
    }
}
