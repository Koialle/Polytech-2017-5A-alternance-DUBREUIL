package com.example.epulapp.quizzandroid.beer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.epulapp.quizzandroid.R;


public class BeerDetail extends Fragment {
    private Beer beer = new Beer();

    public static BeerDetail newInstance() {
        BeerDetail fragment = new BeerDetail();
        return fragment;
    }

    public void setBeer(Beer beer){
        this.beer = beer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_beer, container, false);
        ((TextView)v.findViewById(R.id.detail_beer_name)).setText(beer.getName());
        ((TextView)v.findViewById(R.id.detail_beer_desc)).setText(beer.getDescription());
        ((TextView)v.findViewById(R.id.detail_beer_alc)).setText(String.valueOf(beer.getAbv()));
        ((TextView)v.findViewById(R.id.beer_detail_first_brewed)).setText(beer.getFirst_brewed());
        ((ImageView)v.findViewById(R.id.beer_detail_image)).setImageBitmap(beer.getImage());
        return v;
    }
}
