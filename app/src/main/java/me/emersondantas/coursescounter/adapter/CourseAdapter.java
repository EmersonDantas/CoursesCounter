package me.emersondantas.coursescounter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.emersondantas.coursescounter.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_list, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("NOME");
        holder.currentLesson.setText("AULA ATUAL");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView numOfLessons;
        TextView hoursOfTheCourse;
        TextView  currentLesson;
        TextView linkForTheCourse;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            currentLesson = itemView.findViewById(R.id.tvCurrentLesson);
        }
    }
}
