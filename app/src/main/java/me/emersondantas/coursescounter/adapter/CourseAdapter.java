package me.emersondantas.coursescounter.adapter;

import android.content.Context;
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
import android.widget.Toast;

import java.util.List;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.SQLiteDataBaseHelper;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private List<Course> courses;
    private LinearLayout lastSelected;
    private int lastSelectedPosition;
    private SQLiteDataBaseHelper<Course> courseDao;
    private Context menuActivity;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public LinearLayout getLastselected() {
        return lastSelected;
    }

    public void setLastSelected(LinearLayout layout){ this.lastSelected = layout; }

    public void setLastSelectedPosition(int position){ this.lastSelectedPosition = position;}

    public int getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    public CourseAdapter(List<Course> courses, SQLiteDataBaseHelper courseDao, Context context) {
        this.courses = courses;
        this.courseDao = courseDao;
        this.menuActivity = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_list, parent, false);
        MyViewHolder holder = new MyViewHolder(itemLista);
        //onClickButtons(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.name.setText(course.getName());
        holder.currentLesson.setText(String.valueOf(course.getCurrentLesson()));
        LinearLayout layoutList = holder.root;
        //onClickInCourse(layoutList, holder, position);
    }

    /*
    private void onClickInCourse(LinearLayout layoutList, MyViewHolder holder, int position){
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
    }
    */

    public void removeSelectedCourse(){
        courseDao.deleteFrom(courses.get(lastSelectedPosition));
        updateLocalList();
        notifyItemRemoved(lastSelectedPosition);
        notifyDataSetChanged();
    }

    public void addNewCourse(Course course){
        courseDao.insertInTo(course);
        updateLocalList();
        notifyItemInserted(getItemCount()); //added in last positon
        notifyDataSetChanged();
    }

    public void updateLocalList(){
        courses.clear();
        courses = courseDao.selectAllFromTable();
        Toast.makeText(menuActivity, "Total de cursos:" + String.valueOf(getItemCount()),Toast.LENGTH_LONG).show();
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
