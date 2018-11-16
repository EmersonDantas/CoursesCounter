package me.emersondantas.coursescounter.model.bean;

/**
 *
 * @author Emerson Dantas
 */
public class User{
    private int id;
    private String name;
    private String mail;
    private String pass;

    public User(int id, String name, String mail, String pass) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    public User(String name, String mail, String pass) {
        this(0, name, mail, pass);
    }

    public int getId() {
        return this.id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getMail() { return mail; }

    public String getPass() { return pass; }

    public void setPass(String pass) { this.pass = pass; }

    @Override
    public String toString() {
        return name + " - " + mail;
    }
}
