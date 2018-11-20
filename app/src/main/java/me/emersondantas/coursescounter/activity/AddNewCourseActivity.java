package me.emersondantas.coursescounter.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.adapter.CourseAdapter;
import me.emersondantas.coursescounter.exception.InvalidInputException;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;

public class AddNewCourseActivity extends AppCompatActivity {
    private Button btnBack, btnRegister;
    private EditText etName, etNumLessons, etNumHours, etCurrentLesson, etLink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etNumLessons = findViewById(R.id.etNumLessons);
        etNumHours = findViewById(R.id.etNumHours);
        etCurrentLesson = findViewById(R.id.etNumCurrentLesson);
        etLink = findViewById(R.id.etLink);
        listeneningBtnBack();
        listeningBtnRegister();

    }

    public void listeneningBtnBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void listeningBtnRegister(){
        btnRegister.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Course newCourse = null;
                try {
                    newCourse = catchingDados();
                    CourseDAO.getInstance(getApplicationContext()).insertInTo(newCourse);
                    clearAllImput();
                    showSnackBarResult(v, true);
                    CourseAdapter.getInstance().updateRecyclerView();
                } catch (InvalidInputException e) {
                    showSnackBarResult(v, false);
                }
            }
        });

    }

    private Course catchingDados() throws InvalidInputException {
        int numLessons = 0, numHours = 0, currentLesson = 0;
        Drawable erroIcon = getResources().getDrawable(R.drawable.ic_error_red_24dp);
        String erro = "";

        String name = etName.getText().toString();
        if(name.equals("")){
            etName.setCompoundDrawablesWithIntrinsicBounds(null, null, erroIcon, null);
            erro += "Name input is empty" + " - ";
        }else{
            etName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        try {
            numLessons = Integer.parseInt(etNumLessons.getText().toString());
            etNumLessons.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }catch(NumberFormatException ex){
            etNumLessons.setCompoundDrawablesWithIntrinsicBounds(null, null, erroIcon, null);
            erro += "Lessons number is empty" + " - ";
        }

        try {
            numHours = Integer.parseInt(etNumHours.getText().toString());
            etNumHours.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }catch(NumberFormatException ex){
            etNumHours.setCompoundDrawablesWithIntrinsicBounds(null, null, erroIcon, null);
            erro += "Hours number is empty" + " - ";
        }

        try {
            currentLesson = Integer.parseInt(etCurrentLesson.getText().toString());
            etCurrentLesson.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }catch(NumberFormatException ex){
            etCurrentLesson.setCompoundDrawablesWithIntrinsicBounds(null, null, erroIcon, null);
            erro += "Current lesson number is empty" + " - ";
        }

        String link = etLink.getText().toString();
        if(link.equals("")){
            etLink.setCompoundDrawablesWithIntrinsicBounds(null, null, erroIcon, null);
            erro += "Link is empty";
        }else{
            etLink.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        if(erro.equals("")) {
            return new Course(name, numLessons, numHours, currentLesson, link);
        }else{
            throw new InvalidInputException(erro);
        }
    }

    private void clearAllImput(){
        etName.setText("");
        etNumLessons.setText("");
        etNumHours.setText("");
        etCurrentLesson.setText("");
        etLink.setText("");
    }

    private void showSnackBarResult(View v, boolean result){
        Snackbar resultBar = Snackbar.make(v, "result", Snackbar.LENGTH_LONG);

        if(result == true){
            resultBar.setText("Curso cadastrado com sucesso!");
            resultBar.setAction("Confirmar", new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            resultBar.setText("Preencha os campos corretamente!");
        }

        resultBar.show();
    }
}
