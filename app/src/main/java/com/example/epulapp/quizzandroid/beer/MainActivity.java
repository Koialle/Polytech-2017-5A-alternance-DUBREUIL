package com.example.epulapp.quizzandroid.beer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.epulapp.quizzandroid.R;
import com.example.epulapp.quizzandroid.beer.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements BeerFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BeerFragment fragment = new BeerFragment();
        fragmentTransaction.add(R.id.fragment_container_beers, fragment);
        fragmentTransaction.commit();
    }

    @Override
    //public void onListFragmentInteraction(DummyContent.DummyItem item) {
    public void onListFragmentInteraction(Beer item) {
        //@TODO
    }
}
