package me.emersondantas.coursescounter.connection;

/**
 *
 * @author Emerson Dantas
 */
public class ConnectionFactoryMySQL extends ConnectionFactoryDB{
    private static ConnectionFactoryMySQL instance;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3366/coursescounter";
    private final String USER = "root";
    private final String PASS = "";
    
    private ConnectionFactoryMySQL(){}
    
    public static ConnectionFactoryMySQL getInstance(){
        if(instance == null){
            instance = new ConnectionFactoryMySQL();
        }
        
        return instance;
    }

    @Override
    public String getDRIVER() {
        return this.DRIVER;
    }

    @Override
    public String getURL() {
        return this.URL;
    }

    @Override
    public String getUSER() {
        return this.USER;
    }

    @Override
    public String getPASS() {
        return this.PASS;
    }
}
