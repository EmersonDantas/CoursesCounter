package me.emersondantas.coursescounter.adapter;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.activity.MenuActivity;
import me.emersondantas.coursescounter.fragment.EditCourseFragment;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private static CourseAdapter instance;
    private List<Course> courses;
    private ConstraintLayout lastSelected;
    private int lastSelectedPosition;
    private SQLiteDataBaseHelper<Course> courseDao;
    private MyViewHolder holder;

    private CourseAdapter() {
        this.courses = courses;
        this.courseDao = CourseDAO.getInstance(null);
        this.courses = courseDao.selectAllFromTable();
    }

    public static CourseAdapter getInstance(){
        if(instance == null){
            instance = new CourseAdapter();
        }
        return instance;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_list, parent, false);
        holder = new MyViewHolder(itemLista);
        onClickButtons(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.name.setText(course.getName());
        holder.currentLesson.setText(String.valueOf(course.getCurrentLesson()));
        ConstraintLayout layoutList = holder.root;
        onClickInCourse(layoutList, holder, position);
    }

    private void onClickInCourse(ConstraintLayout layoutList, MyViewHolder holder, int position){
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(lastSelected != null) {
                    lastSelected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                }

                if(layoutList.equals(lastSelected)){
                    lastSelected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                    lastSelected = null;

                }else {
                    layoutList.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.VISIBLE);
                    lastSelected = layoutList;
                    lastSelectedPosition = position;
                }
            }
        });

    }

    private void onClickButtons(MyViewHolder holder){
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                removeSelectedCourse();
            }
        });

        holder.btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                MenuActivity.onClickEditCourse(new MenuActivity.OnClickCourseListener() {
                    @Override
                    public Course onClick() {
                        return courses.get(lastSelectedPosition);
                    }
                });
            }
        });

        holder.btnInfo.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MenuActivity.onClickInfoCourse(new MenuActivity.OnClickCourseListener() {
                    @Override
                    public Course onClick() {
                        return courses.get(lastSelectedPosition);
                    }
                });
            }
        });

        holder.btnIncrementLesson.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }

    private void removeSelectedCourse(){
        lastSelected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
        courseDao.deleteFrom(courses.get(lastSelectedPosition));
        notifyItemRemoved(lastSelectedPosition);
        updateRecyclerView();
    }

    private void updateLocalList(){
        courses.clear();
        courses = courseDao.selectAllFromTable();
    }

    public void updateRecyclerView(){
        updateLocalList();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, currentLesson;
        Button btnDelete, btnEdit, btnInfo, btnIncrementLesson;
        ProgressBar progressBar;
        ConstraintLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            currentLesson = itemView.findViewById(R.id.tvCurrentLesson);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnIncrementLesson = itemView.findViewById(R.id.btnIncrementLesson);
            progressBar = itemView.findViewById(R.id.progressBar);
            root = (ConstraintLayout) itemView;
        }
    }
}
