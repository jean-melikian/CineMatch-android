package com.cinematching.application.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinematching.application.R;
import com.cinematching.application.models.Movie;
import com.cinematching.application.views.holder.MoviesListViewHolder;

import java.util.List;

/**
 * Created by meryl on 08/10/2017.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListViewHolder> {

    List<Movie> movies;

    public MoviesListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MoviesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cell;
        cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_movies, parent, false);
        return new MoviesListViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(MoviesListViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

