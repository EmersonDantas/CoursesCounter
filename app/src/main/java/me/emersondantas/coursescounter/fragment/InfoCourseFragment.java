package me.emersondantas.coursescounter.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.activity.MenuActivity;
import me.emersondantas.coursescounter.model.bean.Course;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCourseFragment extends Fragment {
    private Button btnBack;
    private static Course courseSelected;
    private View view;
    private TextInputEditText etName, etNumLessons, etNumHours, etNumCurrentLesson, etLink;
    public InfoCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        loadComponents();
    }

    private void loadComponents(){
        btnBack = view.findViewById(R.id.btnBack);
        etName = view.findViewById(R.id.etName);
        etNumLessons = view.findViewById(R.id.etNumLessons);
        etNumHours =  view.findViewById(R.id.etNumHours);
        etNumCurrentLesson = view.findViewById(R.id.etNumCurrentLesson);
        etLink = view.findViewById(R.id.etLink);
        listenigButtons();
        fillComponents();
        setEditTextsEditableFalse();
    }

    private void listenigButtons(){
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MenuActivity.onClickBackInFragment();
            }
        });
    }

    private void fillComponents(){
        etName.setText(courseSelected.getName());
        etNumLessons.setText(String.valueOf(courseSelected.getNumOfLessons()));
        etNumHours.setText(String.valueOf(courseSelected.getHoursOfTheCourse()));
        etNumCurrentLesson.setText(String.valueOf(courseSelected.getCurrentLesson()));
        etLink.setText(courseSelected.getLinkForTheCourse());
    }

    private void setEditTextsEditableFalse(){
        etName.setEnabled(false);
        etNumLessons.setEnabled(false);
        etNumHours.setEnabled(false);
        etNumCurrentLesson.setEnabled(false);
        etLink.setEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_info_course, container, false);
        return view;
    }

    public static void updateSelectedCourse(UpdateSelectedCourse on){
        courseSelected = on.update();
    }

    public interface UpdateSelectedCourse{
        Course update();
    }

}
