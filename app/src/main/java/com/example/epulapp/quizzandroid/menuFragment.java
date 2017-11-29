package com.example.epulapp.quizzandroid;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    public interface MenuCallback {
        public void onButtonClicked(View view);
    }

    private MenuCallback activity;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@TODO callback to activity
                activity.onButtonClicked(view);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activity = (MenuCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.valueOf(context) + " must implement onButtonClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
