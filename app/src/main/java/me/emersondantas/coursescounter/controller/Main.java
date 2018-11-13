package me.emersondantas.coursescounter.controller;
import java.util.List;

import me.emersondantas.coursescounter.model.bean.*;
import me.emersondantas.coursescounter.model.dao.*;

/**
 *
 * @author Emerson Dantas
 */
public class Main {
    public static void main(String[] args) {
        DataAccessObject<Course> cd = CourseDAO.getInstance();
        DataAccessObject<User> ud = UserDAO.getInstance();

        //ud.add(new User("Emerson", "emerson.ruan@dce.ufpb.br", "sucodemaracuja"));
        //cd.add(new Course("Banco de dados MySQL", 50, 150, 25, "jdbc.course.net"));

        List<Course> courses = cd.read();
        List<User> users = ud.read();
        
        for(Course c: courses) {
            System.out.println(c.getName());
        }

        System.out.println(ud.entityExists(users.get(0)));

        for(User u: users){
            System.out.println(u.getName());
        }
    }
}
