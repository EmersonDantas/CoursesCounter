package me.emersondantas.coursescounter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coursesDAO = CourseDAO.getInstance(getApplicationContext());
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        coursesList = fillCoursesList();
        adapter = new CourseAdapter(coursesList);
        settingRecycleView();

    }

    public List<Course> fillCoursesList(){
        return coursesDAO.selectAllFromTable();
    }

    public void settingRecycleView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setHasFixedSize(true); // Otimiza a rcv, agora o tamanho é fixo.
        coursesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        coursesRecyclerView.setAdapter(adapter);
    }

    public void addNewCourse(View view){
        coursesDAO.insertInTo(new Course("dança",10,10,10,"www.androiplusplusweb.net"));
        coursesDAO.insertInTo(new Course("Como estudar dormindo",10,10,10,"www.pythonfilosofia.net"));
        coursesDAO.insertInTo(new Course("biscoito ou bolacha",10,10,10,"www.oreonaoorel.net"));
        coursesDAO.insertInTo(new Course("nada de nada",10,10,10,"www.mysqlaquigratis.net"));
    }
}
