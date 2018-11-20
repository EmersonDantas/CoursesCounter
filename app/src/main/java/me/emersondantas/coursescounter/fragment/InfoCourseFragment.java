package me.emersondantas.coursescounter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.emersondantas.coursescounter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoCourseFragment extends Fragment {


    public InfoCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_course, container, false);
    }

}
