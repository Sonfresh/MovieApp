package com.example.sonfresh.movieapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends ArrayAdapter<Movie> {
    Context context;
    //Constructor
    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        this.context = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverView;
        ImageView ivPoster;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the data item for this position
        Movie movie = getItem(position);
        //Chek if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;//View lookup cache stored in tag
        if (convertView==null){
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.txtTitle);
            viewHolder.tvOverView = (TextView)convertView.findViewById(R.id.txtOverView);
            viewHolder.ivPoster = (ImageView)convertView.findViewById(R.id.imgPoster);

            convertView.setTag(viewHolder);
        }
        else{
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvTitle.setText(movie.title);
        viewHolder.tvOverView.setText(movie.overView);
        //ImageView viewImage = (ImageView)convertView.findViewById(R.id.imgPoster);
        //String image;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + movie.posterUrl).into(viewHolder.ivPoster);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path).into(viewHolder.ivPoster);
        }


        //Return the completed view to render on screen
        return convertView;
    }
}
