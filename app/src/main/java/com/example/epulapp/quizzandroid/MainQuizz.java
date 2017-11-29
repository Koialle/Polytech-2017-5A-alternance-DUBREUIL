package com.example.epulapp.quizzandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainQuizz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreateMenu","Menu activity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);
    }
}
