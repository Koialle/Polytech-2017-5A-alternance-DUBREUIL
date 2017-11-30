package com.example.epulapp.quizzandroid.beer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.epulapp.quizzandroid.R;
import com.example.epulapp.quizzandroid.beer.BeerFragment.OnListFragmentInteractionListener;
import java.io.InputStream;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Beer} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.ViewHolder> {

    private final List<Beer> mValues;
    private final OnListFragmentInteractionListener mListener;

    public BeerRecyclerViewAdapter(List<Beer> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_beer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beer beer = mValues.get(position);
        holder.beer = beer;
        holder.nameView.setText(beer.getName());
        holder.alcView.setText("Alc." + beer.getAbv());
        new DownloadImageTask(holder.imageView).execute(beer.image_url);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.beer);
            }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameView;
        public final TextView alcView;
        public final ImageView imageView;
        public Beer beer;

        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) itemView.findViewById(R.id.textview_beer_name);
            alcView = (TextView) itemView.findViewById(R.id.textview_beer_alc);
            imageView = (ImageView) itemView.findViewById(R.id.imageview_beer);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
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
            imageView.setImageBitmap(result);
        }
    }
}
