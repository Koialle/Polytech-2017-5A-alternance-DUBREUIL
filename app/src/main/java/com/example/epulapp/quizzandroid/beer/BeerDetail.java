package com.example.epulapp.quizzandroid.beer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.epulapp.quizzandroid.R;


public class BeerDetail extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        int alc = bundle.getInt("alc");

        View view = inflater.inflate(R.layout.fragment_detail_beer, container, false);
        // Set the Text to try this out
        TextView txtOne = (TextView) view.findViewById(R.id.detail_beer_name);
        txtOne.setText(name);
        TextView txtTwo = (TextView) view.findViewById(R.id.detail_beer_desc);
        txtTwo.setText(description);
        TextView txtThree = (TextView) view.findViewById(R.id.detail_beer_alc);
        txtThree.setText(String.valueOf(alc));
        return view;
    }
}
