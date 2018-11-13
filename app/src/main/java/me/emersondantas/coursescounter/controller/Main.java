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
        DataAccessObject<User> ud = UserDAO.getInstance();

        //ud.add(new User("Emerson", "emerson.ruan@dce.ufpb.br", "sucodemaracuja"));
        
        for(Course c: cd.read()){
            System.out.println(c.getName());
        }

        for(User u: ud.read()){
            System.out.println(u.getName());
        }
    }
}
