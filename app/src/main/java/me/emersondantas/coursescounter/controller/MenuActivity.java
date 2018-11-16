package me.emersondantas.coursescounter.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class MenuActivity extends AppCompatActivity {
    private SQLiteDataBaseHelper<Course> coursesDbh;
    private ListView coursesListView;
    private List<Course> coursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coursesDbh = CourseDAO.getInstance(getApplicationContext());
        coursesListView = findViewById(R.id.coursesListView);
        coursesList = fillCoursesListView();
        setAdapterForCoursesListView(coursesList);

        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicou", Toast.LENGTH_LONG).show();
            }
        });


    }

    public List<Course> fillCoursesListView(){
        return coursesDbh.selectAllFromTable();
    }
    public void setAdapterForCoursesListView(List<Course> coursesList){
        ArrayAdapter<Course> arrayAdUser = new ArrayAdapter<Course>(getApplicationContext(), android.R.layout.simple_list_item_1, coursesList);
        coursesListView.setAdapter(arrayAdUser);
    }

    public void addNewCourse(View view){
        //coursesDbh.insertInTo(new Course("Banco de dados MySQL",10,10,10,"www.databasemysql.net"));
    }
}
