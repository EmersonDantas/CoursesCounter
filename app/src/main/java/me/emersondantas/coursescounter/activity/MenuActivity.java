package me.emersondantas.coursescounter.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.adapter.CourseAdapter;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class MenuActivity extends AppCompatActivity {
    private SQLiteDataBaseHelper<Course> courseDao;
    private RecyclerView coursesRecyclerView;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        courseDao = CourseDAO.getInstance(getApplicationContext());
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        adapter = CourseAdapter.getInstance();
        settingRecycleView();

    }

    public void settingRecycleView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setHasFixedSize(true); // Otimiza a rcv, agora o tamanho é fixo.
        coursesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        coursesRecyclerView.setAdapter(adapter);
    }

    public void addNewCourse(View view){
        Intent intent = new Intent(getApplicationContext(), AddNewCourseActivity.class);
        startActivity(intent);
        //testAdd();
    }

    public void testAdd(){
        Course c1 = new Course("Teste 1", 1, 1, 1, "www");
        Course c2 = new Course("Teste 2", 1, 1, 1, "www");
        Course c3 = new Course("Teste 3", 1, 1, 1, "www");
        Course c4 = new Course("Teste 4", 1, 1, 1, "www");
        Course c5 = new Course("Teste 5", 1, 1, 1, "www");
        Course c6 = new Course("Teste 6", 1, 1, 1, "www");
        Course c7 = new Course("Teste 7", 1, 1, 1, "www");
        Course c8 = new Course("Teste 8", 1, 1, 1, "www");
        Course c9 = new Course("Teste 9", 1, 1, 1, "www");
        Course c10 = new Course("Teste 10", 1, 1, 1, "www");

        courseDao.insertInTo(c1);
        courseDao.insertInTo(c2);
        courseDao.insertInTo(c3);
        courseDao.insertInTo(c4);
        courseDao.insertInTo(c5);
        courseDao.insertInTo(c6);
        courseDao.insertInTo(c7);
        courseDao.insertInTo(c8);
        courseDao.insertInTo(c9);
        courseDao.insertInTo(c10);

        CourseAdapter.getInstance().updateRecyclerView();
    }

}
