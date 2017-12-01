package com.example.epulapp.quizzandroid.beer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.epulapp.quizzandroid.R;

/**
 * Main activity of <g>ZeBeers</g>. Composed of two fragments : BeerFragment (beer list) and BeerDetail
 */
public class MainActivity extends AppCompatActivity implements BeerFragment.OnListFragmentInteractionListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting main layout
        setContentView(R.layout.activity_main);
        // Setting first fragment
        BeerFragment fragment = new BeerFragment();
        changeFragment(fragment, true);
    }

    @Override
    public void onListFragmentInteraction(Beer item) {
        BeerDetail fragment = new BeerDetail();
        fragment.setBeer(item);
        changeFragment(fragment, false);
    }

    public void changeFragment(Fragment fragment, boolean isFirst){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isFirst) {
            fragmentTransaction.add(R.id.fragment_container_beers, fragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_container_beers, fragment);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
