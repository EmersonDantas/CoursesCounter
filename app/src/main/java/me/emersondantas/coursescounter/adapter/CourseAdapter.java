package me.emersondantas.coursescounter.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.model.bean.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private List<Course> courses;
    private LinearLayout lastselected;
    private int lastSelectedPosition;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LinearLayout getLastselected() {
        return lastselected;
    }

    public int getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_list, parent, false);
        MyViewHolder holder = new MyViewHolder(itemLista);
        onClickButtons(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.name.setText(course.getName());
        holder.currentLesson.setText(String.valueOf(course.getCurrentLesson()));
        LinearLayout layoutList = holder.root;
        onClickInCourse(layoutList, holder, position);
    }

    private void onClickInCourse(LinearLayout layoutList, MyViewHolder holder, int position){
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(lastselected != null) {
                    lastselected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                }

                if(layoutList.equals(lastselected)){
                    lastselected.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.GONE);
                    lastselected = null;

                }else {
                    layoutList.findViewById(R.id.layButtons).setVisibility(ConstraintLayout.VISIBLE);
                    lastselected = layoutList;
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
    }

    private void removeSelectedCourse(){
        courses.remove(lastSelectedPosition);
        notifyItemRemoved(lastSelectedPosition);
        notifyItemRangeChanged(lastSelectedPosition, getItemCount());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, currentLesson;
        Button btnDelete, btnEdit, btnInfo, btnIncrementLesson;
        ProgressBar progressBar;
        LinearLayout root;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            currentLesson = itemView.findViewById(R.id.tvCurrentLesson);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnIncrementLesson = itemView.findViewById(R.id.btnIncrementLesson);
            progressBar = itemView.findViewById(R.id.progressBar);
            root = (LinearLayout) itemView;
        }
    }
}
