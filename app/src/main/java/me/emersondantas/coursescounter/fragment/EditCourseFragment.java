package me.emersondantas.coursescounter.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.emersondantas.coursescounter.R;
import me.emersondantas.coursescounter.activity.MenuActivity;
import me.emersondantas.coursescounter.model.bean.Course;
import me.emersondantas.coursescounter.model.dao.CourseDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditCourseFragment extends Fragment {
    private Button btnBack, btnSave;
    private static Course courseSelected;
    private View view;
    private TextInputEditText etName, etNumLessons, etNumHours, etNumCurrentLesson, etLink;

    public EditCourseFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_course, container, false);
        loadComponents();
        return view;
    }

    private void loadComponents(){
        btnBack = view.findViewById(R.id.btnBack);
        btnSave = view.findViewById(R.id.btnSave);
        etName = view.findViewById(R.id.etName);
        etNumLessons = view.findViewById(R.id.etNumLessons);
        etNumHours =  view.findViewById(R.id.etNumHours);
        etNumCurrentLesson = view.findViewById(R.id.etNumCurrentLesson);
        etLink = view.findViewById(R.id.etLink);
        listenigButtons();
        fillComponents();
    }

    private void listenigButtons(){
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MenuActivity.onClickBackInFragment();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = courseSelected.getId();
                String name = etName.getText().toString();
                int numLessons = Integer.parseInt(etNumLessons.getText().toString());
                int numHours = Integer.parseInt(etNumHours.getText().toString());
                int currentLesson = Integer.parseInt(etNumCurrentLesson.getText().toString());
                String link = etLink.getText().toString();

                Course courseToUpdate = new Course(id,name,numLessons,numHours,currentLesson,link);
                MenuActivity.onClickSaveInFragment(courseToUpdate);
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

    public static void updateSelectedCourse(UpdateSelectedCourse on){
        courseSelected = on.update();
    }

    public interface UpdateSelectedCourse{
        Course update();
    }
}
