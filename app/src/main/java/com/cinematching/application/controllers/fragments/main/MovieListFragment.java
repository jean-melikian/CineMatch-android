package com.cinematching.application.controllers.fragments.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinematching.application.R;
import com.cinematching.application.adapters.MoviesListAdapter;
import com.cinematching.application.controllers.fragments.BaseFragment;
import com.cinematching.application.models.Movie;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.retrofit.MovieService;

import java.util.List;

/**
 * Created by meryl on 08/10/2017.
 */

public class MovieListFragment extends BaseFragment {

    private static String fragmentTitle = "Movie Screening List";
    MovieService movieService;
    private RecyclerView movieRecyclerView;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);

        setFragmentTitle(fragmentTitle);

        movieRecyclerView = (RecyclerView) view.findViewById(R.id.moviesList_recyclerView);

        movieService = new MovieService();

        MoviesListAdapter moviesListAdapter = new MoviesListAdapter(getActivity(), null);

        movieRecyclerView.setAdapter(moviesListAdapter);

        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieService.getScreening(new IServiceResultListener<List<Movie>>() {
            @Override
            public void onResult(ServiceResult<List<Movie>> result) {
                moviesListAdapter.setMovies(result.getData());
            }
        });

        return view;
    }

    @Override
    public String getFragmentTitle() {
        return MovieListFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        MovieListFragment.fragmentTitle = fragmentTitle;
    }
}
