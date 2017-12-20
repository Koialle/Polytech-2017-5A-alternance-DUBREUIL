package com.example.epulapp.quizzandroid.beer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.epulapp.quizzandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of <g>Beers.</g>
 */
public class BeerFragment extends Fragment {
    private static final String PUNK_API_URL = "https://api.punkapi.com/v2/";
    private static final String ARG_COLUMN_COUNT = "column-count";
    private List<Beer> listBeers = new ArrayList<>();
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BeerFragment() {
    }

    public static BeerFragment newInstance(int columnCount) {
        BeerFragment fragment = new BeerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);

        new MyAsyncTask().execute();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }

        return view;
    }

    private class MyAsyncTask extends AsyncTask<Object, Integer, List<Beer>> {
        @Override
        protected List<Beer> doInBackground(Object... params) {
            // Getting beer list from API
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(PUNK_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitService service = retrofit.create(RetrofitService.class);
            Call<List<Beer>> beers = service.getBeers();

            try {
                listBeers = beers.execute().body();
            } catch (IOException exception) {
                Log.e("beers", exception.getStackTrace().toString());
            }

            return listBeers;
        }

        @Override
        protected void onPostExecute(List<Beer> beers) {
            super.onPostExecute(beers);

            BeerRecyclerViewAdapter adapter = new BeerRecyclerViewAdapter(beers, mListener);

            for (Beer beer: beers) {
                beer.addObserver(adapter);
                new DownloadImageTask(beer).execute(beer.getImage_url());
            }

            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Beer item);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private Beer beer;

        public DownloadImageTask(Beer beer) {
            this.beer = beer;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            beer.setImage(result);
        }
    }
}
