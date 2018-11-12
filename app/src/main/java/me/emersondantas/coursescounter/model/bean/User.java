package me.emersondantas.coursescounter.model.bean;
import me.emersondantas.coursescounter.model.dao.BaseEntity;

/**
 *
 * @author Emerson Dantas
 */
public class User implements BaseEntity{
    private long id;
    private String userName;
    private String pass;
    
    @Override
    public long getId() {
        return this.id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
