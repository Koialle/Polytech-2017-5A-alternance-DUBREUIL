package com.example.epulapp.quizzandroid.beer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.epulapp.quizzandroid.R;
import com.example.epulapp.quizzandroid.beer.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements BeerFragment.OnListFragmentInteractionListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BeerFragment fragment = new BeerFragment();
        fragmentTransaction.add(R.id.fragment_container_beers, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onListFragmentInteraction(Beer item) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BeerDetail fragment = new BeerDetail();
        fragmentTransaction.replace(R.id.fragment_container_beers, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
