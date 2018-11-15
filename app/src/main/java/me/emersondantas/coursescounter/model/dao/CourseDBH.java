package me.emersondantas.coursescounter.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import me.emersondantas.coursescounter.model.bean.Course;

public class CourseDBH extends DataBaseHelper {
    public CourseDBH(@Nullable Context context) {
        super(context);
    }

    @Override
    protected String getComparableCondition(Object obg) {
        Course course = (Course) obg;
        String sql = "nameOfCourse = '" + course.getName() + "' and numOfLessons = '" + course.getNumOfLessons() + "'";
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
        return "id INT(3) AUTOINCREMENT PRIMARY KEY, nameOfCourse VARCHAR(30) NOT NULL, numOfLessons INT(3) NOT NULL, hoursOfTheCourse INT(3) NOT NULL, currentLesson INT(3) DEFAULT 0, linkForTheCourse VARCHAR(60)";
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
}
