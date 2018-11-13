package me.emersondantas.coursescounter.model.dao;

import me.emersondantas.coursescounter.connection.ConnectionFactoryMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emerson Dantas
 */
public abstract class DataAccessObject<T extends BaseEntity> {
    
    public void add(T obg){
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con =  ConnectionFactoryMySQL.getInstance().getConnection();
            stmt = prepareStatementForCreate(obg, con);
            stmt.execute();
            System.out.println("Salvo no banco de dados com sucesso!");
            ConnectionFactoryMySQL.getInstance().closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar no banco de dados: "+ex.getMessage());
        }
    }
    
    public List<T> read(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> attributes = new ArrayList<>();
        try {
            con =  ConnectionFactoryMySQL.getInstance().getConnection();
            stmt = PreparedStatementForRead(con);
            rs = stmt.executeQuery();
            while(rs.next()){
                T attribute = readLine(rs);
                attributes.add(attribute);
            }
            ConnectionFactoryMySQL.getInstance().closeConnection(con, stmt, rs);
        } catch (SQLException ex) {
            System.out.println("Erro ao ler dados do banco: "+ex.getMessage());
        }
        
        return attributes;
    }
    
    public void modify(T obg){
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con =  ConnectionFactoryMySQL.getInstance().getConnection();
            stmt = prepareStatementForUpdate(obg, con);
            stmt.execute();
            System.out.println("Entidade atualizado com sucesso!");
            ConnectionFactoryMySQL.getInstance().closeConnection(con, stmt);
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar curso: "+ex.getMessage());
        }
    }

    public void remove(T obg){
        try{
            Connection con = ConnectionFactoryMySQL.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM "+ getTableName() + " WHERE id = ?");
            stmt.setInt(1, obg.getId());
            stmt.execute();
            System.out.println("Entidade removida com sucesso");
        } catch(SQLException ex){
            System.out.println("Erro ao remover entidade do banco");
        }
    }
    
    public Long getLastID(){
        try {
            Connection con = ConnectionFactoryMySQL.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM "+getTableName());
            ResultSet rs = stmt.executeQuery();
            rs.last();
            return rs.getLong(1);
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar lastId.");
            return null;
        }
    }

    protected abstract PreparedStatement PreparedStatementForRead(Connection con);
    protected abstract PreparedStatement prepareStatementForCreate(T obg, Connection con);
    protected abstract PreparedStatement prepareStatementForUpdate(T obg, Connection con);
    protected abstract T readLine(ResultSet rs) throws SQLException;
    protected abstract String getTableName();
    public abstract boolean entityExists(T obg);
}
