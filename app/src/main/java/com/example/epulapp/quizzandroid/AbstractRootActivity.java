package com.example.epulapp.quizzandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.epulapp.quizzandroid.R.id.button2;

public class AbstractRootActivity extends AppCompatActivity implements MenuFragment.MenuCallback {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);

        fragmentManager = getFragmentManager();
        MenuFragment menu = new MenuFragment();
        changeFragment(menu, false);
    }

    public void changeFragment(Fragment frag, boolean isReplace){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isReplace){
            fragmentTransaction.replace(R.id.fragment_container, frag);
        } else {
            fragmentTransaction.add(R.id.fragment_container, frag);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(View view) {
        QuizzFragment question = new QuizzFragment();
        changeFragment(question, true);
    }
}
