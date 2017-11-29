package com.example.epulapp.quizzandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class QuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreateQuizz","Create quizz activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }
}
