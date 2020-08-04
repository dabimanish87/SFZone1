package com.example.sfzone.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sfzone.AttendanceReport;
import com.example.sfzone.EventActivity;
import com.example.sfzone.GruhUdhyog;
import com.example.sfzone.NotesActivity;
import com.example.sfzone.R;
import com.example.sfzone.ScanActivity;
import com.example.sfzone.TimeTableActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView timetableImageView = (ImageView) root.findViewById(R.id.timetableImageView);
        timetableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timetableIntent = new Intent(getContext(),TimeTableActivity.class);
                startActivity(timetableIntent);
            }
        });

        ImageView notesImageView = (ImageView) root.findViewById(R.id.notesImageView);
        notesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notesIntent = new Intent(getContext(), NotesActivity.class);
                startActivity(notesIntent);
            }
        });

        ImageView eventsImageView = (ImageView) root.findViewById(R.id.eventsImageView);
        eventsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventsIntent = new Intent(getContext(), EventActivity.class);
                startActivity(eventsIntent);
            }
        });



        ImageView scanqrImageView = (ImageView) root.findViewById(R.id.scanqrImageView);
        scanqrImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventsIntent = new Intent(getContext(), ScanActivity.class);
                startActivity(eventsIntent);

            }
        });

        ImageView reportImageView = (ImageView) root.findViewById(R.id.attendance_report_image_view);
        reportImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventsIntent = new Intent(getContext(), AttendanceReport.class);
                startActivity(eventsIntent);

            }
        });

       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;

    }


}