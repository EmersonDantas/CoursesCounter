package me.emersondantas.coursescounter.controller;
import me.emersondantas.coursescounter.model.bean.*;
import me.emersondantas.coursescounter.model.dao.*;

/**
 *
 * @author Emerson Dantas
 */
public class Main {
    public static void main(String[] args) {
        DataAccessObject<Course> cd = CourseDAO.getInstance();
        
        for(Course c: cd.read()){
            System.out.println(c.getName());
        }
        
        System.out.println(cd.getLastID());
    }
}
