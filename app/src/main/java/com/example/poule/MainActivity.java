package com.example.poule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                openActivity(LoginActivity.class);
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 1500);

    }

    public void openActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}