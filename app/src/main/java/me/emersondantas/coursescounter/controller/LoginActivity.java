package me.emersondantas.coursescounter.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import me.emersondantas.coursescounter.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void login(View view){
        EditText mailEdTxt = findViewById(R.id.edtTxMail);
        EditText passEdTxt = findViewById(R.id.edtTxPass);

        String mail = mailEdTxt.toString();
        System.out.println(mail);
    }
}
