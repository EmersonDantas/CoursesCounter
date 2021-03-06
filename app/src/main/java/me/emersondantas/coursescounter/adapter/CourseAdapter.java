package me.emersondantas.coursescounter.adapter;

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
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;
import me.emersondantas.coursescounter.model.dao.DataAccessObject;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private static CourseAdapter instance;
    private List<Course> courses;
    private ConstraintLayout lastSelected;
    private int lastSelectedPosition;
    private DataAccessObject<Course> courseDao;
    private MyViewHolder holder;

    private CourseAdapter() {
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
        chargeProgressBar(holder, course);
    }

    private void chargeProgressBar(MyViewHolder holder, Course course){
        double dv = (double) course.getCurrentLesson() / course.getNumOfLessons();
        double porcento = dv * 100;
        String resPorcento = String.format("%.0f", porcento);
        holder.tvProgress.setText("Progresso: " + resPorcento + "%");
        holder.progressBar.setMax(course.getNumOfLessons());
        holder.progressBar.setProgress(course.getCurrentLesson());
    }

    public void updateListWithQuery(List<Course> result){
        updateRecyclerView(result);
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
                lastSelected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                MenuActivity.onClickDeleteCourse(new MenuActivity.OnClickCourseListener() {
                    @Override
                    public Course onClick() {
                        return courses.get(lastSelectedPosition);
                    }
                });
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
                lastSelected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                MenuActivity.onClickInIncrementLesson(new MenuActivity.OnClickCourseListener() {
                    @Override
                    public Course onClick() {
                        return courses.get(lastSelectedPosition);
                    }
                });
            }
        });
    }

    private void updateLocalList(List<Course> coursesIn){
        this.courses.clear();
        this.courses = coursesIn;
    }

    public void updateRecyclerView(List<Course> courses){
        updateLocalList(courses);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, currentLesson, tvProgress;
        Button btnDelete, btnEdit, btnInfo, btnIncrementLesson;
        ProgressBar progressBar;
        ConstraintLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            currentLesson = itemView.findViewById(R.id.tvCurrentLesson);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnIncrementLesson = itemView.findViewById(R.id.btnIncrementLesson);
            progressBar = itemView.findViewById(R.id.progressBar);
            root = (ConstraintLayout) itemView;
        }
    }
}
