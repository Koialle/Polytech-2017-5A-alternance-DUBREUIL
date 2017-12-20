package com.example.epulapp.quizzandroid.beer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epulapp.quizzandroid.R;

import java.util.Observable;
import java.util.Observer;

/**
 * A fragment representing a single <g>Beer</g>
 */
public class BeerDetail extends Fragment implements Observer {
    private Beer beer = new Beer();
    private View view;

    public static BeerDetail newInstance() {
        BeerDetail fragment = new BeerDetail();
        return fragment;
    }

    public void setBeer(Beer beer){
        this.beer = beer;
        this.beer.addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_beer, container, false);
        this.updateView(v);

        return v;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Beer) {
            ((ImageView)view.findViewById(R.id.beer_detail_image)).setImageBitmap(((Beer)observable).getImage());
        }
    }

    private void updateView(View v) {
        this.view = v;
        ((TextView)view.findViewById(R.id.detail_beer_name)).setText(beer.getName());
        ((TextView)view.findViewById(R.id.detail_beer_desc)).setText(beer.getDescription());
        ((TextView)view.findViewById(R.id.detail_beer_desc)).setMovementMethod(new ScrollingMovementMethod());
        ((TextView)view.findViewById(R.id.detail_beer_alc)).setText(String.valueOf(beer.getAbv()));
        ((TextView)view.findViewById(R.id.beer_detail_first_brewed)).setText(beer.getFirst_brewed());
        ((TextView)view.findViewById(R.id.beer_detail_contributed)).setText(beer.getContributed_by());
        ((ImageView)view.findViewById(R.id.beer_detail_image)).setImageBitmap(beer.getImage());
        ((TextView)view.findViewById(R.id.beer_detail_tips)).setText(beer.getBrewers_tips());
        ((TextView)view.findViewById(R.id.beer_detail_tips)).setMovementMethod(new ScrollingMovementMethod());
    }
}
