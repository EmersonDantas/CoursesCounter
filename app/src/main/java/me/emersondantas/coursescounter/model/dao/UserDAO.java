package me.emersondantas.coursescounter.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import me.emersondantas.coursescounter.model.bean.User;

public class UserDAO extends DataAccessObject {
    private static UserDAO instance;

    private UserDAO(@Nullable Context context) {
        super(context);
        createTable();
    }

    @Override
    protected String getSearchCondition() {
        return "name";
    }

    @Override
    protected String getUpdateRegisterCondition(Object obg) {
        return null;
    }

    @Override
    protected String getRemovingCondition(Object obg) {
        User user = (User) obg;
        return "id = " + user.getId();
    }

    @Override
    public String getFindingCondition(Object obg) {
        User user = (User) obg;
        return "mail = '" + user.getMail() + "' and pass = '" + user.getPass()+"'";
    }

    public static UserDAO getInstance(Context context){
        if(instance == null){
            instance = new UserDAO(context);
        }

        return instance;
    }

    @Override
    protected Object createRegisterObject(Cursor cr) {
        int id = cr.getInt(0);
        String name = cr.getString(1);
        String mail = cr.getString(2);
        String pass = cr.getString(3);
        User newUser = new User(id, name, mail, pass);
        return newUser;
    }

    @Override
    protected String getCreateTableDescriptions() {
        return "id INT(3) PRIMARY KEY, name VARCHAR(70) NOT NULL, mail VARCHAR(50) NOT NULL UNIQUE, pass VARCHAR(16) NOT NULL";
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected ContentValues prepareContentValues(ContentValues cv, Object obg) {
        User user = (User) obg;
        cv.put("name", user.getName());
        cv.put("mail", user.getMail());
        cv.put("pass", user.getPass());
        return  cv;
    }
}
