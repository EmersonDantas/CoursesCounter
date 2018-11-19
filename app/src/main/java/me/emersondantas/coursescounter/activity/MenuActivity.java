package me.emersondantas.coursescounter.activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.adapter.CourseAdapter;
import me.emersondantas.coursescounter.controller.RecyclerCourseClickListener;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class MenuActivity extends AppCompatActivity {
    private SQLiteDataBaseHelper<Course> coursesDao;
    private RecyclerView coursesRecyclerView;
    private List<Course> coursesList;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coursesDao = CourseDAO.getInstance(getApplicationContext());
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        coursesList = fillCoursesList();
        adapter = new CourseAdapter(coursesList, coursesDao, getApplicationContext());
        settingRecycleView();

    }

    public List<Course> fillCoursesList(){
        return coursesDao.selectAllFromTable();
    }

    public void settingRecycleView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setHasFixedSize(true); // Otimiza a rcv, agora o tamanho é fixo.
        coursesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        coursesRecyclerView.setAdapter(adapter);
        clickInRecyclerViewEvent();
    }

    public void addNewCourse(View view){
        Course test = new Course("aaaa",9,1,1,"test");
        adapter.addNewCourse(test);
    }

    public void clickInRecyclerViewEvent(){
        coursesRecyclerView.addOnItemTouchListener( new RecyclerCourseClickListener(
                getApplicationContext(),
                coursesRecyclerView,
                new RecyclerCourseClickListener.OnCourseClickListener() {
                    @Override
                    public void onCourseClick(View view, int position) {
                        LinearLayout root = (LinearLayout) view;
                        buttonsLayoutClick(root.findViewById(R.id.layButtons));
                        eventVisibilitLayoutButtons(root, position);
                    }

                    @Override
                    public void onLongCourseClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));
    }

    public void eventVisibilitLayoutButtons(LinearLayout root, int position){
        LinearLayout lastselected = adapter.getLastselected();
        if(lastselected != null) {
            lastselected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
        }

        if(root.equals(lastselected)){
            lastselected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
            adapter.setLastSelected(null);

        }else {
            root.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.VISIBLE);
            adapter.setLastSelected(root);
            adapter.setLastSelectedPosition(position);
        }
    }

    public void buttonsLayoutClick(ConstraintLayout layButtons){
        Button deleteButon = layButtons.findViewById(R.id.btnDelete);
        deleteButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                adapter.removeSelectedCourse();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
