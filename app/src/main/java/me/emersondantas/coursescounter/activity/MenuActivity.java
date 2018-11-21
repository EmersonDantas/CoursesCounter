package me.emersondantas.coursescounter.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.adapter.CourseAdapter;
import me.emersondantas.coursescounter.fragment.EditCourseFragment;
import me.emersondantas.coursescounter.fragment.InfoCourseFragment;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView coursesRecyclerView;
    private SearchView searchViewMenu;
    private static CourseDAO courseDao;
    private static CourseAdapter adapter;
    private static EditCourseFragment editFrame;
    private static FragmentManager fragmentManager;
    private static Course courseSelectedInList;
    private static InfoCourseFragment infoFragment;
    private static AlertDialog.Builder alertDialog;
    private static Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        courseDao = CourseDAO.getInstance(getApplicationContext());
        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
        searchViewMenu = findViewById(R.id.searchView);
        adapter = CourseAdapter.getInstance();
        settingRecycleView();
        settingSearchView();
        editFrame = new EditCourseFragment();
        infoFragment = new InfoCourseFragment();
        fragmentManager = getSupportFragmentManager();
        thisContext = getApplicationContext();
        alertDialog = new AlertDialog.Builder(this);
    }

    public void settingRecycleView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coursesRecyclerView.setLayoutManager(layoutManager);
        coursesRecyclerView.setHasFixedSize(true); // Otimiza a rcv, agora o tamanho é fixo.
        coursesRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        coursesRecyclerView.setAdapter(adapter);
    }

    public void settingSearchView(){
        searchViewMenu.setQueryHint("Buscar cursos");
        searchViewMenu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Course> queryResult = courseDao.searchStartsWith(newText);
                adapter.updateListWithQuery(queryResult);
                return true;
            }
        });
    }

    public void addNewCourse(View view){
        Intent intent = new Intent(getApplicationContext(), AddNewCourseActivity.class);
        startActivity(intent);
        //testAdd();
    }

    public void testAdd(){
        Course c1 = new Course("Android Oreo", 530, 93, 105, "https://www.udemy.com/curso-de-desenvolvimento-android-oreo/learn/v4/overview");
        Course c2 = new Course("Design Responsivo", 80, 25, 1, "https://www.udemy.com");
        Course c3 = new Course("Django", 100, 30, 1, "https://www.udemy.com");
        Course c4 = new Course("Design Thinking", 50, 15, 1, "https://www.udemy.com");
        Course c5 = new Course("PHP orientado a objetos", 1, 1, 1, "https://www.udemy.com");
        Course c6 = new Course("Amazon AWS", 30, 10, 1, "https://www.udemy.com");
        Course c7 = new Course("C e C++", 60, 15, 1, "https://www.udemy.com");
        Course c8 = new Course("Linux Server", 55, 20, 1, "https://www.udemy.com");
        Course c9 = new Course("SQL Server", 1, 1, 1, "https://www.udemy.com");
        Course c10 = new Course("MySQL", 40, 22, 1, "https://www.udemy.com");
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

        CourseAdapter.getInstance().updateRecyclerView(courseDao.selectAllFromTable());
    }

    public static void onClickInfoCourse(OnClickCourseListener hook){
        courseSelectedInList = hook.onClick();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameEditAndInfo, infoFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        InfoCourseFragment.updateSelectedCourse(new InfoCourseFragment.UpdateSelectedCourse(){
            @Override
            public Course update() {
                return courseSelectedInList;
            }
        });

    }

    public static void onClickBackInFragment(){
        fragmentManager.popBackStack();
    }

    public static void onClickSaveInFragment(Course courseToUpdate){
        courseDao.updateRegister(courseToUpdate);
        adapter.updateRecyclerView(courseDao.selectAllFromTable());
        fragmentManager.popBackStack();
        Toast.makeText(thisContext, "Curso salvo!", Toast.LENGTH_LONG).show();
    }

    public static void onClickInIncrementLesson(OnClickCourseListener hook){
        courseSelectedInList = hook.onClick();
        courseDao.incrementCurrentLesson(courseSelectedInList);
        adapter.updateRecyclerView(courseDao.selectAllFromTable());
    }

    public static void onClickDeleteCourse(OnClickCourseListener hook){
        courseSelectedInList = hook.onClick();
        alertDialog.setTitle("Excluindo");
        alertDialog.setIcon(android.R.drawable.ic_menu_delete);
        alertDialog.setMessage("Tem certeza que deseja apagar o curso " + courseSelectedInList.getName() + "?");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                courseDao.deleteFrom(courseSelectedInList);
                adapter.updateRecyclerView(courseDao.selectAllFromTable());
                Toast.makeText(thisContext, "Curso excluido com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }

    public static void onClickEditCourse(OnClickCourseListener hook){
        courseSelectedInList = hook.onClick();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameEditAndInfo, editFrame);
        transaction.addToBackStack(null);
        transaction.commit();

        EditCourseFragment.updateSelectedCourse(new EditCourseFragment.UpdateSelectedCourse() {
            @Override
            public Course update() {
                return courseSelectedInList;
            }
        });
    }

    public interface OnClickCourseListener{
        Course onClick();
    }

}
