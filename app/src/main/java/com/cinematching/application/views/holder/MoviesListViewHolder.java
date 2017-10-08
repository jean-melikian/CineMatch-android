package com.cinematching.application.views.holder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinematching.application.R;
import com.cinematching.application.controllers.MainActivity;
import com.cinematching.application.models.Movie;

import java.net.URI;

/**
 * Created by meryl on 08/10/2017.
 */

public class MoviesListViewHolder extends RecyclerView.ViewHolder {

    TextView movieCellTitle;
    TextView movieCellDate;
    ImageView movieCellImage;

    private Movie currentMovie;

    public MoviesListViewHolder(View itemView){
        super(itemView);
        movieCellTitle = (TextView) itemView.findViewById(R.id.moviesList_title);
        movieCellDate = (TextView) itemView.findViewById(R.id.moviesList_date);
        movieCellImage = (ImageView) itemView.findViewById(R.id.moviesList_image);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getContext() instanceof MainActivity) {
                    ((MainActivity)v.getContext()).displayListMovies();
                }

            }
        });
    }

    public void bind(Movie movie){
        currentMovie = movie;
        if (movie.getName() != null){
            movieCellTitle.setText(movie.getName());
        }
        if(movie.getReleaseDate() != null){
            movieCellDate.setText(movie.getReleaseDate());
        }
        if(movie.getImageUrl() != null){
            movieCellImage.setImageURI(movie.getImageUrl());
        }
    }
}
