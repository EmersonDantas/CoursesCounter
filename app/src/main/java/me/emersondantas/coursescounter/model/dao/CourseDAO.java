package me.emersondantas.coursescounter.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import me.emersondantas.coursescounter.model.bean.Course;

public class CourseDAO extends DataAccessObject {
    private static CourseDAO instance;

    private CourseDAO(@Nullable Context context) {
        super(context);
        createTable();
    }

    @Override
    protected String getSearchCondition() {
        return "nameOfCourse";
    }

    @Override
    protected String getUpdateRegisterCondition(Object obg) {
        Course course = (Course) obg;
        return "nameOfCourse = '" + course.getName() + "', numOfLessons = '" + course.getNumOfLessons() + "', hoursOfTheCourse = '" + course.getHoursOfTheCourse() + "', currentLesson = '" + course.getCurrentLesson() + "', linkForTheCourse = '" + course.getLinkForTheCourse() + "' WHERE id = '" + course.getId() + "'";
    }

    @Override
    protected String getRemovingCondition(Object obg) {
        Course course = (Course) obg;
        return "id = '" + course.getId() + "'";
    }

    public static CourseDAO getInstance(Context contex){
        if(instance == null){
            instance = new CourseDAO(contex);
        }
        return instance;
    }

    @Override
    protected String getFindingCondition(Object obg) {
        Course course = (Course) obg;
        String sql = "nameOfCourse = '" + course.getName() + "' AND numOfLessons = '" + course.getNumOfLessons() + "'";
        return sql;
    }

    @Override
    protected Object createRegisterObject(Cursor cr) {
        int id = cr.getInt(0);
        String nameOfCourse = cr.getString(1);
        int numOfLessons = cr.getInt(2);
        int hoursOfTheCourse = cr.getInt(3);
        int currentLesson = cr.getInt(4);
        String linkForTheCourse = cr.getString(5);
        Course newCourse = new Course(id, nameOfCourse, numOfLessons, hoursOfTheCourse, currentLesson, linkForTheCourse);
        return newCourse;
    }

    @Override
    protected String getCreateTableDescriptions() {
        return "id INTEGER PRIMARY KEY, nameOfCourse VARCHAR(30) NOT NULL, numOfLessons INT(3) NOT NULL, hoursOfTheCourse INT(3) NOT NULL, currentLesson INT(3) DEFAULT 0, linkForTheCourse VARCHAR(60)";
    }

    @Override
    protected String getTableName() {
        return "courses";
    }

    @Override
    protected ContentValues prepareContentValues(ContentValues cv, Object obg) {
        Course course = (Course) obg;
        cv.put("nameOfCourse", course.getName());
        cv.put("numOfLessons", course.getNumOfLessons());
        cv.put("hoursOfTheCourse", course.getHoursOfTheCourse());
        cv.put("currentLesson", course.getCurrentLesson());
        cv.put("linkForTheCourse", course.getLinkForTheCourse());
        return cv;
    }

    public void incrementCurrentLesson(Course course){
        String sql = "UPDATE courses SET currentLesson = '" + (course.getCurrentLesson()+1) +"' WHERE id = '" + course.getId() + "'";
        super.executeSql(sql);
    }
}
