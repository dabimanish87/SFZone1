package com.example.sfzone.ui.notification;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sfzone.NotesActivity;
import com.example.sfzone.R;
import com.example.sfzone.TimeTableActivity;

public class Notification extends Fragment {

    private NotificationViewModel mViewModel;

    public static Notification newInstance() {
        return new Notification();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notification_fragment, container, false);

        Button openButton = (Button) view.findViewById(R.id.openButton);

        Button openButton2 = (Button) view.findViewById(R.id.openButton2);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent notificationIntent = new Intent(getApplication, TimeTableActivity.class);
                Intent notificationIntent = new Intent(getActivity(),TimeTableActivity.class);
                startActivity(notificationIntent);
            }
        });

        openButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent notificationIntent = new Intent(getApplication, TimeTableActivity.class);
                Intent notificationIntent = new Intent(getActivity(), NotesActivity.class);
                startActivity(notificationIntent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        // TODO: Use the ViewModel
    }

}
