package me.emersondantas.coursescounter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.bean.User;

public class UserDAO extends DataAccessObject {
    private static UserDAO instance;

    private UserDAO() { }

    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    protected PreparedStatement PreparedStatementForRead(Connection con) {
        try {
            return con.prepareStatement("SELECT * FROM users", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement.");
            return null;
        }
    }

    @Override
    protected PreparedStatement prepareStatementForCreate(BaseEntity obg, Connection con) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO users (name, mail, pass) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            User user = (User) obg;
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getMail());
            stmt.setString(3, user.getPass());
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement para adicionar.");
        }
        return stmt;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(BaseEntity obg, Connection con) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE users SET name = ?, pass = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            User user = (User) obg;
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPass());
            stmt.setInt(3, user.getId());
        } catch (SQLException ex) {
            System.out.println("Erro ao criar prepared statement para modificar objeto.");
        }

        return stmt;
    }

    @Override
    protected BaseEntity readLine(ResultSet rs) throws SQLException {
        User user = new User(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("mail"),
                rs.getString("pass"));
        return (BaseEntity) user;
    }
}
