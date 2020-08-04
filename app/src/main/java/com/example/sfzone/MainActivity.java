package com.example.sfzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        For Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent=new Intent(MainActivity.this,Login2Activity.class);
                startActivity(loginIntent);
                onBackPressed();
            }
        },2000);
    }
}
