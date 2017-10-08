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
import com.cinematching.application.controllers.fragments.BaseFragment;
import com.cinematching.application.models.Movie;

import java.util.List;

/**
 * Created by meryl on 08/10/2017.
 */

public class MovieListFragment extends BaseFragment {
    private static String fragmentTitle = "Movie List";
    List<Movie> movies;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;


    public MovieListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.moviesList_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.moviesList_swipeRefresh);

        initView();
        return view;
    }

    private void initView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        getMovies();
        recyclerView.setLayoutManager(llm);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
    }

    @Override
    public String getFragmentTitle() {
        return fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        MovieListFragment.fragmentTitle = fragmentTitle;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void getMovies(){

    }

    public static MovieListFragment newInstance() {

        Bundle args = new Bundle();

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
