package me.emersondantas.coursescounter.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emerson Dantas
 */
public abstract class ConnectionFactoryDB {
    public abstract String getDRIVER();

    public abstract String getURL();

    public abstract String getUSER();

    public abstract String getPASS();
    
    public Connection getConnection() throws SQLException{
        try {
            Class.forName(getDRIVER());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada. Erro: "+ex.getMessage());
        }
        
        return DriverManager.getConnection(getURL(), getUSER(), getPASS());
    }
    
    public void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: "+ex.getMessage());
            }
        }
    }
    
    public void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: "+ex.getMessage());
            }
        }
    }
    
    public void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: "+ex.getMessage());
            }
        }
    }
}
