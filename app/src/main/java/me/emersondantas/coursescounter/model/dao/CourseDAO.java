package me.emersondantas.coursescounter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.emersondantas.coursescounter.connection.ConnectionFactoryMySQL;
import me.emersondantas.coursescounter.model.bean.Course;

/**
 * @author Emerson Dantas
 */
public class CourseDAO extends DataAccessObject {
    private int lastId;
    private static CourseDAO instance;

    private CourseDAO() {
    }

    public static CourseDAO getInstance() {
        if (instance == null) {
            instance = new CourseDAO();
        }

        return instance;
    }

    @Override
    public PreparedStatement prepareStatementForCreate(BaseEntity obg, Connection con) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO courses (nameOfCourse, numOfLessons, hoursOfTheCourse, currentLesson, linkForTheCourse) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            Course course = (Course) obg;
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getNumOfLessons());
            stmt.setInt(3, course.getHoursOfTheCourse());
            stmt.setInt(4, course.getCurrentLesson());
            stmt.setString(5, course.getLinkForTheCourse());
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement para adicionar.");
        }
        return stmt;
    }

    @Override
    public PreparedStatement prepareStatementForUpdate(BaseEntity obg, Connection con) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE courses SET nameOfCourse = ?, numOfLessons = ?, hoursOfTheCourse = ?, currentLesson = ?, linkForTheCourse = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            Course course = (Course) obg;
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getNumOfLessons());
            stmt.setInt(3, course.getHoursOfTheCourse());
            stmt.setInt(4, course.getCurrentLesson());
            stmt.setString(5, course.getLinkForTheCourse());
            stmt.setLong(6, course.getId());
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement para modificar objeto.");
        }

        return stmt;
    }

    @Override
    public BaseEntity readLine(ResultSet rs) throws SQLException {
        Course course = new Course(rs.getInt("id"),
                rs.getString("nameOfCourse"),
                rs.getInt("numOfLessons"),
                rs.getInt("hoursOfTheCourse"),
                rs.getInt("currentLesson"),
                rs.getString("linkForTheCourse"));
        return (BaseEntity) course;
    }

    @Override
    protected String getTableName() {
        return "courses";
    }

    @Override
    public boolean entityExists(BaseEntity obg) {
        try {
            Course course = (Course) obg;
            Connection con = ConnectionFactoryMySQL.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM courses WHERE name = ? and numOfLessons = ?");
            stmt.setString(1,course.getName());
            stmt.setInt(2, course.getNumOfLessons());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if(rs.getInt(1) == 1){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar se entidade existe.");
            return false;
        }
    }

    @Override
    public PreparedStatement PreparedStatementForRead(Connection con) {
        try {
            return con.prepareStatement("SELECT * FROM courses", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement.");
            return null;
        }
    }
}
