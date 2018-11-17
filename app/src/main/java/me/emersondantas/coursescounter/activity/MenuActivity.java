package me.emersondantas.coursescounter.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.adapter.CourseAdapter;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class MenuActivity extends AppCompatActivity {
    private SQLiteDataBaseHelper<Course> coursesDAO;
    private RecyclerView coursesRecyclerView;
    private List<Course> coursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coursesDAO = CourseDAO.getInstance(getApplicationContext());
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        coursesList = fillCoursesListView();

        //Configurando RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setHasFixedSize(true); // Otimiza a rcv, agora o tamanho é fixo.

        //Configurando adapter da RecyclerView
        CourseAdapter adapter = new CourseAdapter();
        coursesRecyclerView.setAdapter(adapter);

        //setAdapterForCoursesListView(coursesList);

    }

    public List<Course> fillCoursesListView(){
        return coursesDAO.selectAllFromTable();
    }
    public void setAdapterForCoursesListView(List<Course> coursesList){
        //ArrayAdapter<Course> arrayAdUser = new ArrayAdapter<Course>(getApplicationContext(), android.R.layout.simple_list_item_1, coursesList);
        //coursesRcv.setAdapter(arrayAdUser);
    }

    public void addNewCourse(View view){
        //coursesDbh.insertInTo(new Course("Banco de dados MySQL",10,10,10,"www.databasemysql.net"));
    }
}
