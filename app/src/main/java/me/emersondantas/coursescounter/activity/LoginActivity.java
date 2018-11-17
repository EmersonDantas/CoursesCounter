package me.emersondantas.coursescounter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.model.bean.User;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;
import me.emersondantas.coursescounter.model.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {
    private SQLiteDataBaseHelper<User> dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataBase = UserDAO.getInstance(getApplicationContext());
    }

    public void login(View view){
        User utest = new User("Emerson", "emerson.ruan@dce.ufpb.br", "sucodemaracuja");
        EditText mailEdTxt = findViewById(R.id.edtTxMail);
        EditText passEdTxt = findViewById(R.id.edtTxPass);

        String mail = mailEdTxt.getText().toString();
        String pass = passEdTxt.getText().toString();

        User us = new User("", mail, pass);
        boolean exists = dataBase.RegisterExists(us);

        if(exists){
            Toast.makeText(getApplicationContext(),"Usuario existe no banco de dados", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Usuario nao existe no banco de dados", Toast.LENGTH_LONG).show();
        }

    }
}
